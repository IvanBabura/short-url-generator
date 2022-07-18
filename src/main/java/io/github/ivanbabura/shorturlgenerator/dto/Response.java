package io.github.ivanbabura.shorturlgenerator.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class Response{
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String error;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String path;
    //TODO: Do something with the path.

    public Response(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = status.getReasonPhrase();
        this.message = message;
        //this.path = ???
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
