package jrb.testetecnico.attus.shared.handler;

import jakarta.servlet.http.HttpServletRequest;
import jrb.testetecnico.attus.shared.exception.EntityNotFoundExcepion;
import jrb.testetecnico.attus.shared.exception.dto.ExceptionDefautlDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorFormatJsonExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ExceptionDefautlDto handler(HttpMessageNotReadableException ex, HttpServletRequest request) {
        return ExceptionDefautlDto
                .builder()
                .mensagem("Json com problemas na formatação. Verifique o padrão das entradas de dados.")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .pathRequest(request.getRequestURI())
                .build();
    }
}
