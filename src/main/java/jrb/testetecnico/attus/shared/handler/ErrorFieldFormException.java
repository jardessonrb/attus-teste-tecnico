package jrb.testetecnico.attus.shared.handler;

import jakarta.servlet.http.HttpServletRequest;
import jrb.testetecnico.attus.shared.exception.dto.ExceptionDefautlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorFieldFormException {

    @Autowired
    private MessageSource messsageSource;

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionDefautlDto handle(MethodArgumentNotValidException exception, HttpServletRequest request){
        List<String> erros = new ArrayList<>();
        List<FieldError> camposComErros = exception.getBindingResult().getFieldErrors();

        camposComErros.forEach(erro -> {
            String mensagemErroValidacao = messsageSource.getMessage(erro, LocaleContextHolder.getLocale());
            String mesagemCampoErro = "[Campo:"+erro.getField()+", Erro:"+mensagemErroValidacao+"]";

            erros.add(mesagemCampoErro);
        });

        return ExceptionDefautlDto
                .builder()
                .mensagem("Campos Inválidos: "+erros.stream().collect(Collectors.joining(", ")))
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .pathRequest(request.getRequestURI())
                .build();
    }
}
