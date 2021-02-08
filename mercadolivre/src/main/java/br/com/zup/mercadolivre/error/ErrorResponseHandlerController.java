package br.com.zup.mercadolivre.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorResponseHandlerController {


    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorReponseHandlerDto>> responseMethodArgumentNotValidException(MethodArgumentNotValidException error) {
        List<ErrorReponseHandlerDto> listaErrorsCapturados = new ArrayList<>();
        for (FieldError fieldError : error.getBindingResult().getFieldErrors()) {
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            listaErrorsCapturados.add(new ErrorReponseHandlerDto(fieldError.getField(), mensagem));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaErrorsCapturados);
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ErrorReponseHandlerDto>> responseBindException(BindException error) {
        List<ErrorReponseHandlerDto> listaErrorsCapturados = new ArrayList<>();


        for (ObjectError objectError :   error.getBindingResult().getAllErrors()) {


            String mensagem = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
            listaErrorsCapturados.add(new ErrorReponseHandlerDto(objectError.getObjectName(), mensagem));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaErrorsCapturados);
    }
}
