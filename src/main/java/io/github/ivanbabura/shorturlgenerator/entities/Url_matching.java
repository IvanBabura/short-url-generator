package io.github.ivanbabura.shorturlgenerator.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table()
public class Url_matching {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUrl;

    @Column(nullable = false, unique = true)
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String shortUrl;

    @Column(nullable = false)
    private final Instant dateTime = Instant.now();

    public Url_matching() {
    }

    public Url_matching(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Url_matching(String originalUrl, String shortUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }

    public Url_matching(int idUrl, String originalUrl, String shortUrl) {
        this.idUrl = idUrl;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }

    public Integer getIdUrl() {
        return idUrl;
    }

    public void setIdUrl(Integer idUrl) {
        this.idUrl = idUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url_matching url_matching = (Url_matching) o;
        try {
            return originalUrl.equals(url_matching.originalUrl) && shortUrl.equals(url_matching.shortUrl);
        } catch (NullPointerException e) {
            if (shortUrl == null || url_matching.shortUrl == null)
                return originalUrl.equals(url_matching.originalUrl);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalUrl);
    }
}
