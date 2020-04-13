package com.mountblue.Blogproject.controller;

import com.mountblue.Blogproject.entity.*;
import com.mountblue.Blogproject.repository.ConfirmationTokenRepository;
import com.mountblue.Blogproject.repository.UserRepository;
import com.mountblue.Blogproject.service.EmailSendService;
import com.mountblue.Blogproject.service.RoleService;
import com.mountblue.Blogproject.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/userAcc/")
public class UerAccountController {

    @Autowired
    private UserRepository userrepo;
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService service;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSendService emailSendService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modalAndView, UserData user) {
        UserData existingUser = userrepo.findByEmailIgnoreCase(user.getEmail());
        if (existingUser != null) {
            modalAndView.addObject("message", "This email already exists!");
            modalAndView.setViewName("error");
        } else {
            userrepo.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            Long id = user.getId();
            String idd = Long.toString(id);
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("Javaprojects20@gmail.com ");
//            mailMessage.setText(user.getPassword());
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://localhost:8081/userAcc/confirm-account?token=" + confirmationToken.getConfirmationToken());
            emailSendService.sendEmail(mailMessage);
            modalAndView.addObject("email", user.getEmail());
            modalAndView.setViewName("successfulRegisteration");

        }
        return modalAndView;
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {

        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            System.out.println(token);
            UserData user = userrepo.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            user.setRole(roleService.get("user"));
            userrepo.save(user);
            modelAndView.setViewName("accountVerified");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @RequestMapping("/Success")
    public String view(Model model) {
        model.addAttribute("users", new UserData());
        return "Success";
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
    public ModelAndView displayResetPassword(ModelAndView modelAndView, UserData user) {
        modelAndView.addObject("users", user);
        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public ModelAndView forgotUserPassword(ModelAndView modelAndView, UserData user) {
        UserData existingUser = userrepo.findByEmailIgnoreCase(user.getEmail());
        if (existingUser != null) {
            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);
            confirmationTokenRepository.save(confirmationToken);
            // Create the email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getEmail());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setFrom("Javaprojects20@gmail.com");
            mailMessage.setText("To complete the password reset process, please click here: "
                    + "http://localhost:8081/confirm-reset?token=" + confirmationToken.getConfirmationToken());

            // Send the email
            emailSendService.sendEmail(mailMessage);
            modelAndView.addObject("message", "Request to reset password received. Check your inbox for the reset link.");
            modelAndView.setViewName("successForgotPassword");

        } else {
            modelAndView.addObject("message", "This email address does not exist!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/confirm-reset", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView validateResetToken(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            UserData user = userrepo.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userrepo.save(user);
            modelAndView.addObject("user", user);
            modelAndView.addObject("emailId", user.getEmail());
            modelAndView.setViewName("resetPassword");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    // Endpoint to update a user's password
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ModelAndView resetUserPassword(ModelAndView modelAndView, UserData user) {
        if (user.getEmail() != null) {
            // Use email to find user
            UserData tokenUser = userrepo.findByEmailIgnoreCase(user.getEmail());
            tokenUser.setPassword(user.getPassword());
            userrepo.save(tokenUser);
            modelAndView.addObject("message", "Password successfully reset. You can now log in with the new credentials.");
            modelAndView.setViewName("successResetPassword");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }





}
