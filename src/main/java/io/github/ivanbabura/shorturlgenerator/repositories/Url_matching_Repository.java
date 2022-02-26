package io.github.ivanbabura.shorturlgenerator.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import java.util.List;

@Repository
public interface Url_matching_Repository
        extends CrudRepository<Url_matching,Integer> {
    Url_matching findByShortUrl(String shortUrl);
    Url_matching findByOriginalUrl(String originalUrl);
    List<Url_matching> findAll();
}