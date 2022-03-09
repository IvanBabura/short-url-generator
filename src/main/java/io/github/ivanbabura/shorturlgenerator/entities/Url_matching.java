package io.github.ivanbabura.shorturlgenerator.entities;

import javax.persistence.*;
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

    public Url_matching() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url_matching url_matching = (Url_matching) o;
        return idUrl.equals(url_matching.idUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUrl);
    }
}
