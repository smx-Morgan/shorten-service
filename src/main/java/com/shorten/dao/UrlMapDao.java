package com.shorten.dao;

import com.shorten.model.UrlMap;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

public interface UrlMapDao extends CrudRepository<UrlMap, Long> {

    UrlMap findFirstByLongUrl(String longUrl);

    List<UrlMap> findByExpireTimeBefore(Instant instant);
}
