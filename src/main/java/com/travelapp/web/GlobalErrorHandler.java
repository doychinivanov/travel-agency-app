package com.travelapp.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ModelAndView handleErrors(NoHandlerFoundException noHandlerFoundException) {
        ModelAndView modelAndView = new ModelAndView("no-such-path");

        return modelAndView;
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleErrors(Exception noHandlerFoundException) {
        ModelAndView modelAndView = new ModelAndView("global-err");

        return modelAndView;
    }

    @ExceptionHandler({RuntimeException.class})
    public ModelAndView handleErrors(RuntimeException runtimeException) {
        System.out.println(runtimeException.getMessage());
        ModelAndView modelAndView = new ModelAndView("global-err");

        return modelAndView;
    }

    @ExceptionHandler({NullPointerException.class})
    public ModelAndView handleErrors(NullPointerException nullPointerException) {
        System.out.println(nullPointerException.getMessage());
        ModelAndView modelAndView = new ModelAndView("global-err");

        return modelAndView;
    }

    @ExceptionHandler({HttpClientErrorException.NotFound.class})
    public ModelAndView handleErrors(HttpClientErrorException httpClientErrorException) {
        System.out.println(httpClientErrorException.getMessage());
        ModelAndView modelAndView = new ModelAndView("global-err");

        return modelAndView;
    }
}
