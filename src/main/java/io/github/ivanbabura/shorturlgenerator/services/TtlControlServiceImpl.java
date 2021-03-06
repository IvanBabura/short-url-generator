package io.github.ivanbabura.shorturlgenerator.services;

import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.Instant;

@Service
public class TtlControlServiceImpl implements TtlControlService {
    private int ttl_in_seconds;

    public TtlControlServiceImpl(ProgramOptionService pos) {
        this.ttl_in_seconds = Integer.parseInt(pos.getValueByNameOption("TTL_in_seconds"));
    }

    @Override
    public boolean checkForExpirationOfTTL(Instant dateTime) {
        Instant dateTimeNow = Instant.now();
        long difference = Duration.between(dateTime, dateTimeNow).toSeconds();
        return difference > ttl_in_seconds;
    }

    public int getTtl_in_seconds() {
        return ttl_in_seconds;
    }

    public void setTtl_in_seconds(int ttl_in_seconds) {
        this.ttl_in_seconds = ttl_in_seconds;
    }
}
