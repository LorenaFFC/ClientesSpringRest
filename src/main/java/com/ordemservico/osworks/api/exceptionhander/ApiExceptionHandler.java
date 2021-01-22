package com.ordemservico.osworks.api.exceptionhander;

import com.ordemservico.osworks.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

// é um componente do spring que trata exceptions
@ControllerAdvice

//ResponseEntityExceptionHandler - essa classe do spring ja tem varias tratativas para erros
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NegocioException.class)  // caso uma determinada foi lancada
    public ResponseEntity<Object> handlerNegocio(NegocioException ex, WebRequest request){
        var status = HttpStatus.BAD_REQUEST;
        var problema = new Problem();
        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(LocalDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var problema = new Problem();
        var campos = new ArrayList<Problem.Campo>();

        for(ObjectError error: ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new Problem.Campo(nome, mensagem));
        }

        problema.setStatus(status.value());
        problema.setTitulo("Um ou mais campos estão inválidos.");
        problema.setDataHora(LocalDateTime.now());
        problema.setCampos(campos);

        return super.handleExceptionInternal(ex, problema, headers, status, request);
    }
}
