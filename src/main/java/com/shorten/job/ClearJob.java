package com.shorten.job;

import com.shorten.dao.UrlMapDao;
import com.shorten.model.UrlMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@Slf4j
public class ClearJob {
    @Autowired
    private UrlMapDao urlMapDao;

//    @Scheduled(fixedRate = 5 * 1000)
    public void clear() {
        //todo 大家可以自己实现分布式定时任务
        log.info("clear job");
        List<UrlMap> list = urlMapDao.findByExpireTimeBefore(Instant.now());
        for (UrlMap urlMap : list) {
            log.info("delete url map {}", urlMap);
            urlMapDao.deleteById(urlMap.getId());
        }
    }
}
