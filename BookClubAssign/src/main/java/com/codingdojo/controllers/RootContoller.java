package com.codingdojo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.codingdojo.models.Book;
import com.codingdojo.models.LoginUser;
import com.codingdojo.models.User;
import com.codingdojo.services.BookService;
import com.codingdojo.services.UserService;


@Controller
public class RootContoller
{
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private BookService bookService;
	
	
//	___________________________________________________________________
//	_______________________RENDER INDEX PAGE___________________________
	
	@GetMapping("/")
	public String index(Model model){
		model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        
        
		return "index";
	}
	
//	__________________________________________________________________
//	_______________________PROCESS CREATING USER______________________
	
	@PostMapping("/newuser")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
        
        if(result.hasErrors()) {
        	model.addAttribute("newLogin",new LoginUser());
        	return "index";
        }
        
        User created_user=userService.register(newUser,result);
        if(created_user==null) {
        	model.addAttribute("newLogin",new LoginUser());
        	return "index";
        }
       
//        SESSION FOR USER TRACKING
        session.setAttribute("user_id", created_user.getId());
        session.setAttribute("user_name", created_user.getUserName());
        return "redirect:/dashboard";
	}

//	__________________________________________________________________
//	_______________________PROCESS LOGGING IN USER______________________
	
	@PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
    
        if(result.hasErrors()) {
        	model.addAttribute("newUser", new User());
        	return "index";
        }
        
        User valid_user = userService.login(newLogin, result);
        
        if(valid_user==null) {
        	model.addAttribute("newUser", new User());
        	return "index";
        }
        
        session.setAttribute("user_id", valid_user.getId());
        session.setAttribute("user_name", valid_user.getUserName());
       
        return "redirect:/dashboard";
        
    
    }
	
//	______________________________________________________________
//	**************************************************************
//						RENDER DASHBOARD
//	______________________________________________________________
//	**************************************************************
	
	@GetMapping("/dashboard")
	public String dashboard(@ModelAttribute("all_users") User user,Model model, HttpSession session) {
		
		if(!userService.isLoggedIN(session)) return "redirect:/";
		
		
		
		List<Book> all_books = bookService.allBooks();
		model.addAttribute("all_books", all_books);
		
		model.addAttribute("user_name", session.getAttribute("user_name"));
		
		
		
		
		return "dashboard";
	}
	
	

	
	
//	______________________________________________________________
//	**************************************************************
//						ADD BOOK ROUTES
//	______________________________________________________________
//	**************************************************************
	
	@GetMapping("/newBook")
	public String addbook(Model model, HttpSession session) {
		
		if(!userService.isLoggedIN(session)) return "redirect:/";
		
		model.addAttribute("book", new Book());
//		
//		information for creating usin session
		model.addAttribute("user_id", session.getAttribute("user_id"));
		return "add_book";
		
	}
	
	
	@PostMapping("/createBook")
	public String creae_book(@Valid @ModelAttribute("book") Book book,BindingResult result, Model model, HttpSession session) {
		
		if(!userService.isLoggedIN(session)) return "redirect:/";
		
		if (result.hasErrors()) {
			return "add_book";
		}else {
			bookService.createBook(book);
			return "redirect:/dashboard";
		}
		
		
	}
	
	
	
	
//	______________________________________________________________
//	**************************************************************
//						RENDER DISPLAY PAGE
//	______________________________________________________________
//	**************************************************************
	@GetMapping("/{id}/show")
	public String show(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if(!userService.isLoggedIN(session)) return "redirect:/";
		
		Book book = bookService.showOne(id);
		model.addAttribute("book", book);
		
		return "show";
	}
	
	
//	**************************************************************
//				RENDER EDIT PAGE
//______________________________________________________________
//**************************************************************
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if(!userService.isLoggedIN(session)) return "redirect:/";
		
		Book book = bookService.showOne(id);
		model.addAttribute("book", book);
		
		return "editpage";
	}
	
	
	
//	EDIT PAGE POST
	
	@PostMapping("/update/{id}")
	public String update(@Valid @ModelAttribute("book") Book book,
		BindingResult result, Model model, HttpSession session)
	{
		if(!userService.isLoggedIN(session)) return "redirect:/";
		
		if (result.hasErrors()) {
			return "editpage";
		}
		bookService.updateBook(book);
		return "redirect:/dashboard";
		}
		
		
	
//	LOG OUT
	@GetMapping("/logout")
	public String logOut(HttpSession session) {
		
		userService.LogOut(session);
		return "redirect:/";
	}
}