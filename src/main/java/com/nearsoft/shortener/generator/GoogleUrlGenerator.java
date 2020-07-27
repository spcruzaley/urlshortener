package com.nearsoft.shortener.generator;

import java.util.Random;

public class GoogleUrlGenerator implements UrlShortenerGenerator {

    @Override
    public String generateShortUrl(String url) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int x = ('a' + (new Random()).nextInt(26));
            char c = ((System.currentTimeMillis()+x)%2==0) ? (char) (x - 32) : (char) x;
            builder.append(c);
        }

        return builder.toString();
    }

}
