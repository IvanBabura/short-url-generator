package io.github.ivanbabura.shorturlgenerator.advice;

import io.github.ivanbabura.shorturlgenerator.dto.Response;
import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.exceptions.FoundException;
import io.github.ivanbabura.shorturlgenerator.exceptions.NoContentException;
import io.github.ivanbabura.shorturlgenerator.exceptions.NotFoundException;
import io.github.ivanbabura.shorturlgenerator.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class ControllerAdvice {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleSomeException(BadRequestException e) {
        return new Response(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(FoundException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Url_matching> handleFoundException(FoundException e) {
        return new ResponseEntity<>(e.getFound_url_matching(), HttpStatus.FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleNotFoundException(NotFoundException e) {
        return new Response(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response handleNoContentException(NoContentException e) {
        return new Response(HttpStatus.NO_CONTENT, e.getMessage());
    }
}