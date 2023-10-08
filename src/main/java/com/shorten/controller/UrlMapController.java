package com.shorten.controller;

import com.shorten.common.ResponseResult;
import com.shorten.common.ResultUtils;
import com.shorten.service.UrlMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
public class UrlMapController {
    private static final String DOMAIN = "http://127.0.0.1:8888/";

    @Autowired
    private UrlMapService urlMapService;


    @PostMapping("/shorten")
    public ResponseResult<Map> shorten(@RequestParam("longUrl") String longUrl) {
        String encode = urlMapService.encode(longUrl);
        return ResultUtils.success(Map.of("shortKey", encode,
                "shortUrl", DOMAIN + encode));
    }

    @GetMapping("/{shortKey}")
    public RedirectView redirect(@PathVariable("shortKey") String shortKey) {
        return urlMapService.decode(shortKey).map(RedirectView::new)
                .orElse(new RedirectView("/sorry"));
    }

    @GetMapping("/sorry")
    public String sorry() {
        return "抱歉，未找到页面！";
    }
}
