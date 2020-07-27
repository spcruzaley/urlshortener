package com.nearsoft.shortener.restservice;

import com.nearsoft.shortener.UrlShortenerException;
import com.nearsoft.shortener.service.UrlShortenerService;
import com.nearsoft.shortener.vo.UrlShortenerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UrlShortenerController {

    private static final String URL = "url";
    private static final String URL_NOT_FOUND = "Short url not found";

    @Autowired
    private UrlShortenerService urlShortenerService;

    @GetMapping(value = "/{shorturl}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UrlShortenerVO> getShortenerUrl(@PathVariable("shorturl") String shorturl) {
        UrlShortenerVO urlShortenerVO = new UrlShortenerVO();

        String url = null;
        try {
            url = urlShortenerService.getUrl(shorturl);
        } catch (UrlShortenerException e) {
            urlShortenerVO.setErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(urlShortenerVO);
        }

        if(url == null) {
            urlShortenerVO.setErrorMessage(URL_NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(urlShortenerVO);
        }

        urlShortenerVO.setAlias(shorturl);
        urlShortenerVO.setUrl(url);

        return ResponseEntity.status(HttpStatus.OK).body(urlShortenerVO);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UrlShortenerVO> generateShortenerUrl(@RequestBody Map<String, String> jsonPayload) {
        String url = jsonPayload.get(URL);
        UrlShortenerVO urlVO = new UrlShortenerVO();

        try {
            urlVO.setAlias(urlShortenerService.generateUrl(url));
            urlVO.setUrl(url);
        } catch (UrlShortenerException e) {
            urlVO.setErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(urlVO);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(urlVO);
    }

}
