package ru.tsystem.javaschool.ordinaalena.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.tsystem.javaschool.ordinaalena.exception.ServiceGenericException;

import javax.servlet.http.HttpServletRequest;

/**
 * A controller to dispatch Exceptions if they occurs
 */
@ControllerAdvice
public class GlobalExceptionController {

    private static final Logger logger = Logger.getLogger(GlobalExceptionController.class);

    /**
     * @return 404 page if NoHandlerFoundException thrown
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle() {
        return "404";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(HttpServletRequest request, Exception e) {
        logger.error(request.getRequestURL() + " raised" + e);
        logger.error("Stacktrace:\n");
        ModelAndView model = new ModelAndView("error");
        model.addObject("errMsg", "this is Exception.class");
        return model;
    }

    @ExceptionHandler(ServiceGenericException.class)
    public ModelAndView handleServiceException(ServiceGenericException sge) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("errMsg", sge.getMessage());
        return model;
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView resetLogin() {
        return new ModelAndView("redirect:user/login");
    }

}
