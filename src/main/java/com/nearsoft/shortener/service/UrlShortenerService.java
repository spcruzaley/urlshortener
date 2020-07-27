package com.nearsoft.shortener.service;

import com.nearsoft.shortener.UrlShortenerException;
import com.nearsoft.shortener.generator.GoogleUrlGenerator;
import com.nearsoft.shortener.generator.OthersUrlGenerator;
import com.nearsoft.shortener.generator.UrlShortenerGenerator;
import com.nearsoft.shortener.generator.YahooUrlGenerator;
import com.nearsoft.shortener.utils.UrlShortenerUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Hashtable;

@Service
public class UrlShortenerService {

    private static final String EMPTY_OR_NULL_URL = "URL null or empty is not allowed";
    private static final String NOT_VALID_URL = "URL not valid, exaple: http(s)://www.domain-name.com";
    private static final String GOOGLE = "google";
    private static final String YAHOO = "yahoo";
    private static final String SHORT_URL = "shorturl";

    private Hashtable<String, String> normalUrl;
    private Hashtable<String, String> shortUrl;

    public UrlShortenerService() {
        this.normalUrl = new Hashtable();
        this.shortUrl = new Hashtable();
    }

    @Cacheable(SHORT_URL)
    public String getUrl(String shortUrl) throws UrlShortenerException {
        if(shortUrl == null || shortUrl.isEmpty())
            throw new UrlShortenerException(EMPTY_OR_NULL_URL);

        return this.shortUrl.get(shortUrl);
    }

    @Cacheable(SHORT_URL)
    public String generateUrl(String url) throws UrlShortenerException {
        UrlShortenerGenerator urlShortenerGenerator = setUrl(url);
        String randomString = urlShortenerGenerator.generateShortUrl(url);

        normalUrl.put(url, randomString);
        shortUrl.put(randomString, url);

        return randomString;
    }

    private UrlShortenerGenerator setUrl(String url) throws UrlShortenerException {
        if(url == null || url.isEmpty())
            throw new UrlShortenerException(EMPTY_OR_NULL_URL);
        if(!UrlShortenerUtils.isUrlValid(url))
            throw new UrlShortenerException(NOT_VALID_URL);

        if(url.contains(GOOGLE)) {
            return new GoogleUrlGenerator();
        } else if(url.contains(YAHOO)) {
            return new YahooUrlGenerator();
        }
        return new OthersUrlGenerator();
    }

}
