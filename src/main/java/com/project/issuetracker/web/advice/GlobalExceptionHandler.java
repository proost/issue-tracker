package com.project.issuetracker.web.advice;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public String handleIOException(IOException ex, Model model) {
        ex.printStackTrace();

        model.addAttribute("status", "500");
        model.addAttribute("message", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        ex.printStackTrace();

        model.addAttribute("status", "400");
        model.addAttribute("message", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalArgumentException(IllegalStateException ex, Model model) {
        ex.printStackTrace();

        model.addAttribute("status", "400");
        model.addAttribute("message", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String handleBadCredentialsException(BadCredentialsException ex, Model model) {
        ex.printStackTrace();

        model.addAttribute("status", "400");
        model.addAttribute("message", ex.getMessage());

        return "error";
    }
}
