package riwi.simulacro_SpringBoot.api.error_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import riwi.simulacro_SpringBoot.api.dto.errors.BaseErrorResponse;
import riwi.simulacro_SpringBoot.api.dto.errors.ErrorResponse;
import riwi.simulacro_SpringBoot.api.dto.errors.ErrorsResponse;
import riwi.simulacro_SpringBoot.util.exceptions.IdNotFoundException;
import riwi.simulacro_SpringBoot.util.exceptions.RoleDenegateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestController {
    @ExceptionHandler(IdNotFoundException.class)
    public BaseErrorResponse handleIdNotFound (IdNotFoundException exception){
        return ErrorResponse.builder().code(HttpStatus.BAD_REQUEST.value()).status(HttpStatus.BAD_REQUEST.name()).message(exception.getMessage()).build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleErrors(MethodArgumentNotValidException exception){
        List<Map<String,String>> errorList = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fiel->{
            Map<String,String> error =new HashMap<>();
            error.put("error", fiel.getDefaultMessage());
            error.put("field", fiel.getField());
            errorList.add(error);
        });
        return ErrorsResponse.builder().code(HttpStatus.BAD_REQUEST.value()).status(HttpStatus.BAD_REQUEST.name()).errors(errorList).build();
    }

    @ExceptionHandler(RoleDenegateException.class)
    public BaseErrorResponse handleRoleDenegate(RoleDenegateException exception){
        return ErrorResponse.builder().message(exception.getMessage()).status(HttpStatus.BAD_REQUEST.name()).code(HttpStatus.BAD_REQUEST.value()).build();
    }
}
