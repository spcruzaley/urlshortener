package com.nearsoft.shortener.utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlShortenerUtils {

    public static boolean isUrlValid(String url) {
        try {
            new URL(url).toURI();
        } catch (MalformedURLException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }

        return true;
    }

    public static boolean onlyContainsConsonants(String string) {
        char[] chars = string.toCharArray();

        for (char c : chars) {
            if(!isConsonant(c))
                return false;
        }

        return true;
    }

    public static boolean isAlpha(String string) {
        return string.matches("^[a-zA-Z]*$");
    }

    public static boolean isAlphanumeric(String string) {
        return string.matches("^[a-zA-Z0-9]*$");
    }

    public static boolean isConsonant(char c) {
        return String.valueOf(c).matches("^[b-df-hj-np-tv-xzB-DF-HJ-NP-TV-XZ]*$");
    }

}
