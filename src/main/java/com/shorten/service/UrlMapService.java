package com.shorten.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.shorten.dao.UrlMapDao;
import com.shorten.model.UrlMap;
import com.shorten.utils.Base62Utils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@Slf4j
public class UrlMapService {

    private LoadingCache<String, String> loadingCache;

    @Autowired
    private UrlMapDao urlMapDao;

    @PostConstruct
    public void init() {
        CacheLoader<String, String> cacheLoader = new CacheLoader<>() {
            @Override
            public String load(String s) throws Exception {
                long id = Base62Utils.shortKeyToId(s);
                log.info("load cache: {}", s);
                return urlMapDao.findById(id).map(UrlMap::getLongUrl).orElse(null);
            }
        };

        loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000000) // 设置最大缓存大小
                .build(cacheLoader);
    }

    public String encode(String longUrl) {
        UrlMap urlMap = urlMapDao.findFirstByLongUrl(longUrl);
        if (urlMap == null) {
            urlMap = urlMapDao.save(UrlMap.builder()
                    .longUrl(longUrl)
                    .expireTime(Instant.now().plus(30, ChronoUnit.DAYS))
                    .build());
            log.info("create urlMap:{}", urlMap);
        }
        try{
            return Base62Utils.idToShortKey(urlMap.getId());
        }finally {
            loadingCache.put(Base62Utils.idToShortKey(urlMap.getId()),longUrl);
        }

    }

    public Optional<String> decode(String shortKey) {
        return Optional.ofNullable(loadingCache.getUnchecked(shortKey));
    }
}
