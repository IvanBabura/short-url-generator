package io.github.ivanbabura.shorturlgenerator.exceptions;

import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;

public class FoundException extends RuntimeException {
    Url_matching found_url_matching;

    public FoundException(Url_matching found_url_matching) {
        this.found_url_matching = found_url_matching;
    }

    public FoundException(Url_matching found_url_matching, String message) {
        super(message);
        this.found_url_matching = found_url_matching;
    }

    public Url_matching getFound_url_matching() {
        return found_url_matching;
    }
}
