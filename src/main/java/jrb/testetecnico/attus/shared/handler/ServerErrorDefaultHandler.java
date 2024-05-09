package jrb.testetecnico.attus.shared.handler;

import jakarta.servlet.http.HttpServletRequest;
import jrb.testetecnico.attus.shared.exception.EntityNotFoundExcepion;
import jrb.testetecnico.attus.shared.exception.dto.ExceptionDefautlDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServerErrorDefaultHandler {
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ExceptionDefautlDto handler(RuntimeException ex, HttpServletRequest request) {
        return ExceptionDefautlDto
                .builder()
                .mensagem("Erro interno do servidor")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .pathRequest(request.getRequestURI())
                .build();
    }

}
