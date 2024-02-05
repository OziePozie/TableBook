package org.beauty.tablebook.controllers.restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    //sample handler
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @CrossOrigin(origins = {
            "http://45.151.144.194:3000",
            "http://45.151.144.194:3000",
            "http://45.151.144.194:80"}
    )

    @ExceptionHandler(ServletException.class)
    public @ResponseBody ErrorDTO handleServletException(HttpServletRequest request,
                                                     Exception ex){
        ErrorDTO response = new ErrorDTO (404, ex.getMessage());
        return response;
    }
}
