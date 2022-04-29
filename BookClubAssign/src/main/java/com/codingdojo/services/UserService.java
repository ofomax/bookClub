package com.codingdojo.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.models.LoginUser;
import com.codingdojo.models.User;
import com.codingdojo.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;
    
//    REGISTER USER
    public User register(User user, BindingResult result) {
//        ERROR TRACKER COUNT
    	int errors = 0;
    	
    	User existing_user= userRepository.findByEmail(user.getEmail());
    	if(existing_user!=null) {
    		
    		result.rejectValue("email", "email_invalid", "Email is in use");
    		errors++;
    	}
//    	COMPARING PASSWORD DOESNT MATCH CONFIRM PASSWORD
    	if(!user.getPassword().equals(user.getConfirm())) {
    		result.rejectValue("password", "password_invalid", "Password and Password Confirmation must match!");
    		errors++;
    	}
    	
    	if(errors>0) {
    		return null;
    	}
    	
    	
//    HASHING PASSWORD
    	String hashed_pw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(8));
    	
//    	CHANGE PASSWORD TO HASHEDPASSWORD
    	user.setPassword(hashed_pw);
    	
//    	SAVE USER INTO DB
    	userRepository.save(user);
    	
        return user;
    }
    
//    VALIDATE LOGIN
    public User login(LoginUser form_user, BindingResult result) {
        
    	User db_user= userRepository.findByEmail(form_user.getEmail());
    	int errors=0;
    	
    	if(db_user==null) {
    		result.rejectValue("email","invalid_email", "Email is not found");
    		errors++;
    	}
    	if(db_user!=null) {
    		Boolean pw_match=BCrypt.checkpw(form_user.getPassword(),db_user.getPassword());
    		if(!pw_match) {
    			result.rejectValue("password", "invalied_password", "Password is invalid");
    			errors++;
    			
    		}
    	}
    	
    	if(errors>0) return null;
        return db_user;
    }
    
//    SESSION FUNCTION TO CHECK LOGGED IN
    public Boolean isLoggedIN(HttpSession session) {
    	return session.getAttribute("user_id")!=null;
    }
//  SESSION FUNCTION TO CHECK LOGGED IN
    public void LogOut(HttpSession session) {
    	if(!isLoggedIN(session)) return;
    	session.removeAttribute("user_id");
    }
    
    
//    FIND ALL USERS
    public List<User> allUsers(){
		return (List<User>) userRepository.findAll();
	}
    
//    
    
}
    