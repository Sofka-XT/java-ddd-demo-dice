package org.example.nomemientan.infra.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
 

@ControllerAdvice(annotations=RestController.class)
public class ErrorRestControllerAdvice {
  
    private static final Logger logger = LoggerFactory.getLogger(ErrorRestControllerAdvice.class);
    
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseError handleException(Exception ex, HttpServletRequest request) {
        logger.debug("executing exception handler (REST) ", ex.getCause());
  
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
  
        return new ResponseError(
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST",
                ex.getMessage(),
                request.getRequestURI());
    }
 
}