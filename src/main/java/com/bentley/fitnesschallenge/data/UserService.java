package com.bentley.fitnesschallenge.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bentley.fitnesschallenge.beans.User;
import com.bentley.fitnesschallenge.data.dto.UserDTO;
import com.bentley.fitnesschallenge.exception.EmailExistsException;

@Service
public class UserService {
    @Autowired
    private UserDAO dao; 
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
     
    public User registerNewUserAccount(UserDTO accountDto) 
      throws EmailExistsException {
         
        if (userNameExists(accountDto.getUsername())) {  
            throw new EmailExistsException(
              "There is an account with that email adress: "
              +  accountDto.getUsername());
        }
        User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setUserName(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));

        dao.createNewUser(user);
        return user;
    }
    private boolean userNameExists(String userName) {
        User user = dao.findByUserName(userName);
        System.out.println("user exists? " + ( user != null ));
        if (user != null) {
            return true;
        }
        return false;
    }
    
    public void authenticate( String username, String password) {
    	UsernamePasswordAuthenticationToken authReq
    	 = new UsernamePasswordAuthenticationToken(username, password);
    	Authentication auth = authenticationManager.authenticate(authReq);
    	SecurityContext sc = SecurityContextHolder.getContext();
    	sc.setAuthentication(auth);
    }
}