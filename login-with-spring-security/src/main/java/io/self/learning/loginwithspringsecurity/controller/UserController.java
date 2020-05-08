package io.self.learning.loginwithspringsecurity.controller;

import io.self.learning.loginwithspringsecurity.dao.ConfirmationToken;
import io.self.learning.loginwithspringsecurity.dao.User;
import io.self.learning.loginwithspringsecurity.repository.ConfirmationTokenRepository;
import io.self.learning.loginwithspringsecurity.repository.UserRepository;
import io.self.learning.loginwithspringsecurity.service.SecurityService;
import io.self.learning.loginwithspringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created in login-with-spring-security.
 */
@RestController
public class UserController {

        @Autowired
        private UserService userService;

        @Autowired
        private SecurityService securityService;

        @Autowired
        private UserRepository userRepository;

        // Instantiate our encoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        @Autowired
        private ConfirmationTokenRepository confirmationTokenRepository;

        // Function to handle the login process
        public ModelAndView loginUser(ModelAndView modelAndView, User user) {
                System.out.println("Post Mapping : user from request :: " + user);
    	        User existingUser = userRepository.findByUsernameIgnoreCase(user.getUsername());
                if (existingUser != null) {
                        System.out.println("Post Mapping : user from database :: " + existingUser);
                        // Use encoder.matches to compare raw password with encrypted password
                        if (encoder.matches(user.getPassword(), existingUser.getPassword())){
                                // Successfully logged in
                                modelAndView.addObject("message", "Welcome, " + existingUser.getUsername());
                                modelAndView.addObject("user", existingUser);
                                modelAndView.setViewName("home");
                        } else {
                                // Wrong password
                                modelAndView.addObject("errorMessage", "Incorrect password. Try again.");
                                modelAndView.setViewName("login");
                        }
                } else {
                        modelAndView.addObject("errorMessage", "The username provided does not exist!");
                        modelAndView.setViewName("login");
                }
                return modelAndView;
        }

        @GetMapping({"/", "/login"})
        public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            User user, ModelAndView modelAndView) {
                System.out.println("Get Mapping : user from request :: " + user);
                System.out.println("Get Mapping : error from request :: " + error);
                System.out.println("Get Mapping : logout from request :: " + logout);
                String errorMessage = null;
                if (error != null) {
                        errorMessage = "Username or Password is incorrect !!";
                        modelAndView.addObject("errorMessage", errorMessage);
                        modelAndView.setViewName("login");
                }
                if (logout != null) {
                        String message = "You have been successfully logged out !";
                        modelAndView.addObject("message", message);
                        modelAndView.setViewName("login");
                }

                modelAndView.addObject("user", user);
                if (null == error && null == logout) {
                        modelAndView.setViewName("login");
                }
                return modelAndView;

        }

        @RequestMapping(value = "/logout", method = RequestMethod.GET)
        public String logoutPage(HttpServletRequest request,
            HttpServletResponse response) {
                Authentication auth = SecurityContextHolder.getContext()
                    .getAuthentication();
                if (auth != null) {
                        new SecurityContextLogoutHandler().logout(request, response, auth);
                }
                return "redirect:/login?logout=true";
        }

        @GetMapping(value = "/forgot-password")
        public ModelAndView displayForgotPassword(ModelAndView modelAndView,
            User user) {
                modelAndView.addObject("user", user);
                modelAndView.setViewName("forgot-password");
                return modelAndView;
        }

        @PostMapping(value = "/forgot-password")
        public ModelAndView forgotUserPassword(ModelAndView modelAndView, User user) {
                User existingUser = userRepository.findByUsernameIgnoreCase(user
                    .getUsername());
                System.out.println(existingUser);
                if (existingUser != null) {
                        // Create token
                        ConfirmationToken confirmationToken = new ConfirmationToken(
                            existingUser);

                        // Save it
                        confirmationTokenRepository.save(confirmationToken);

                        modelAndView.setViewName("forgot-password");

                } else {
                        modelAndView.addObject("errorMessage",
                            "This username does not exist!");
                        modelAndView.setViewName("forgot-password");
                }
                return modelAndView;
        }

        @RequestMapping(value = "/reset-password", method = RequestMethod.GET)
        public ModelAndView displayResetPassword(ModelAndView modelAndView,
            User user) {
                modelAndView.addObject("user", user);
                modelAndView.setViewName("reset-password");
                return modelAndView;
        }

        // Endpoint to confirm the token
        @RequestMapping(value = "/confirm-reset", method = { RequestMethod.GET,
            RequestMethod.POST })
        public ModelAndView validateResetToken(ModelAndView modelAndView,
            @RequestParam("token") String confirmationToken) {
                ConfirmationToken token = confirmationTokenRepository
                    .findByConfirmationToken(confirmationToken);

                if (token != null) {
                        User user = userRepository.findByUsernameIgnoreCase(token.getUser()
                            .getUsername());
                        userRepository.save(user);
                        modelAndView.addObject("user", user);
                        modelAndView.addObject("username", user.getUsername());
                        modelAndView.setViewName("reset-password");
                } else {
                        modelAndView.addObject("message", "The link is invalid or broken!");
                        modelAndView.setViewName("error");
                }
                return modelAndView;
        }

        @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
        public ModelAndView resetUserPassword(ModelAndView modelAndView, User user) {
                if (user.getUsername() != null) {
                        // Use email to find user
                        User tokenUser = userRepository.findByUsernameIgnoreCase(user
                            .getUsername());
                        tokenUser.setPassword(encoder.encode(user.getPassword()));
                        userRepository.save(tokenUser);
                        modelAndView.addObject("user", user);
                        modelAndView
                            .addObject("message",
                                "Password successfully reset. You can now log in with the new credentials.");
                        modelAndView.setViewName("login");
                } else {
                        modelAndView.addObject("message", "The link is invalid or broken!");
                        modelAndView.setViewName("error");
                }
                return modelAndView;
        }

        @RequestMapping(value = "/home", method = RequestMethod.GET)
        public ModelAndView showHome(User user,
            ModelAndView modelAndView) {
                Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
                User userFromDb = userRepository.findByUsernameIgnoreCase(loggedInUser.getName());
                if (null == user || null == userFromDb) {
                        modelAndView.addObject("message", "Welcome, " + loggedInUser.getName());
                } else {
                        modelAndView.addObject("message", "Welcome, " + userFromDb.getName());
                        modelAndView.addObject("user", userFromDb);
                }
                modelAndView.setViewName("home");
                return modelAndView;
        }


}