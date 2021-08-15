package dev.malkon.events.error.handler;

import dev.malkon.events.error.domain.RequestError;
import dev.malkon.events.error.exception.InvalidRequestBodyException;
import dev.malkon.events.error.exception.RecordCreationException;
import dev.malkon.events.error.exception.RecordNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestBodyException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public RequestError handleIncorrectRequestBody(InvalidRequestBodyException exception) {
        return new RequestError(BAD_REQUEST, exception, exception.getErrorList());
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    public RequestError handleRecordNotFound(RecordNotFoundException exception) {
        return new RequestError(NOT_FOUND, exception);
    }

    @ExceptionHandler(RecordCreationException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public RequestError handleRecordNotFound(RecordCreationException exception) {
        return new RequestError(BAD_REQUEST, exception);
    }

}