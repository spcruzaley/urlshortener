package com.nearsoft.shortener.generator;

import com.nearsoft.shortener.utils.UrlShortenerUtils;

public class OthersUrlGenerator implements UrlShortenerGenerator {

    @Override
    public String generateShortUrl(String url) {
        char[] chars = url.replace("http", "").toCharArray();
        StringBuilder builder = new StringBuilder();

        for (char c : chars) {
            if(UrlShortenerUtils.isConsonant(c))
                builder.append(c);
        }

        return builder.toString();
    }
}
