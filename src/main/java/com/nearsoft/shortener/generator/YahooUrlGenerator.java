package com.nearsoft.shortener.generator;

import java.util.Random;

public class YahooUrlGenerator implements UrlShortenerGenerator {

    @Override
    public String generateShortUrl(String url) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            int x = ('a' + (new Random()).nextInt(26));
            char c = ((System.currentTimeMillis()+x)%2==0) ? (char) (x - 32) : (char) x;
            int randomInt = new Random().ints(1,10).findFirst().getAsInt();

            builder.append(randomInt%2==0 ? String.valueOf(c) : String.valueOf(randomInt));
        }

        return builder.toString();
    }

}
