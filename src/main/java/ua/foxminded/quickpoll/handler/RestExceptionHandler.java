package ua.foxminded.quickpoll.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.foxminded.quickpoll.Exception.ResourceNotFoundException;
import ua.foxminded.quickpoll.dto.error.ErrorDetail;
import ua.foxminded.quickpoll.dto.error.ValidationError;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleRsourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request){

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(LocalDateTime.now());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());

        return new ResponseEntity<Object>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError (MethodArgumentNotValidException manve, HttpServletRequest request){
        ErrorDetail errorDetail = new ErrorDetail();

        errorDetail.setTimeStamp(LocalDateTime.now());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTitle("Validation Failed");
        errorDetail.setDetail("Input validation failed");
        errorDetail.setDeveloperMessage(manve.getClass().getName());

        // Create validation instance
        List<FieldError> fieldErrors =  manve.getBindingResult().getFieldErrors();
        for (FieldError fieldErr : fieldErrors){
            List<ValidationError> validationErrorList
                    = errorDetail.getErrors().get(fieldErr.getField());
            if(validationErrorList  == null) {
                validationErrorList = new ArrayList<ValidationError>();
                errorDetail.getErrors().put(fieldErr.getField(), validationErrorList);
                ValidationError validationError = new ValidationError();
                validationError.setCode(fieldErr.getCode());
                validationError.setMessage(fieldErr.getDefaultMessage());
                validationErrorList.add(validationError);
            }
        }
        return new ResponseEntity<Object>(errorDetail, null, HttpStatus.BAD_REQUEST);
    }
}
