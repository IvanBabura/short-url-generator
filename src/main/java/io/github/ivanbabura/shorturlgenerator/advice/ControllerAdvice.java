package io.github.ivanbabura.shorturlgenerator.advice;

import io.github.ivanbabura.shorturlgenerator.dto.Response;
import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.exceptions.*;
import io.github.ivanbabura.shorturlgenerator.services.Url_matching_ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class ControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(Url_matching_ServiceImpl.class);

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleSomeException(BadRequestException e) {
        logger.warn(HttpStatus.BAD_REQUEST + ": " + e.getMessage());
        return new Response(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(FoundException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Url_matching> handleFoundException(FoundException e) {
        logger.warn(HttpStatus.FOUND + ": " + e.getMessage());
        return new ResponseEntity<>(e.getFound_url_matching(), HttpStatus.FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleNotFoundException(NotFoundException e) {
        logger.warn(HttpStatus.NOT_FOUND + ": " + e.getMessage());
        return new Response(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response handleNoContentException(NoContentException e) {
        logger.warn(HttpStatus.NO_CONTENT + ": " + e.getMessage());
        return new Response(HttpStatus.NO_CONTENT, e.getMessage());
    }

    @ExceptionHandler(GenerateShortUrlException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleGenerateShortUrlException(GenerateShortUrlException e) {
        logger.warn(HttpStatus.INTERNAL_SERVER_ERROR + ": " + e.getMessage());
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(IncorrectUrlException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleIncorrectUrlException(IncorrectUrlException e) {
        logger.warn(HttpStatus.BAD_REQUEST + ": " + e.getMessage());
        return new Response(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(EndOfLifeUrlException.class)
    public Response handleEndOfLifeUrlException(EndOfLifeUrlException e) {
        logger.warn("Attempt to go to URL with ending TTL: " + e.getMessage());
        return new Response(HttpStatus.NON_AUTHORITATIVE_INFORMATION, e.getMessage());
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleThrowableException(Throwable t){
        String message = "Unexpected error: " + t.getMessage();
        logger.error(message);
        Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR, message);
        return new ResponseEntity<> (response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}