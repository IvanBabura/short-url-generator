package io.github.ivanbabura.shorturlgenerator.services;

import java.time.Instant;

public interface TtlControlService {
    boolean checkForExpirationOfTTL(Instant dateTime);
    int getTtl_in_seconds();
    void setTtl_in_seconds(int ttl_in_seconds);

}
