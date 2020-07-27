package com.nearsoft.shortener;

import com.nearsoft.shortener.service.UrlShortenerService;
import com.nearsoft.shortener.utils.UrlShortenerUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlShortenerServiceTests {

	@Autowired
	private UrlShortenerService urlShortenerService;

	@Test
	void malformedUrlTest() {
		String malformedUrl = "some-url";
		Assert.assertFalse(UrlShortenerUtils.isUrlValid(malformedUrl));
	}

	@Test
	void generateUrlForGoogleTest() throws UrlShortenerException {
		String googleUrl = "https://www.google.com";
		String shortUrlGenerated = urlShortenerService.generateUrl(googleUrl);

		Assert.assertTrue(shortUrlGenerated.length() == 5);
		Assert.assertTrue(UrlShortenerUtils.isAlpha(shortUrlGenerated));
	}

	@Test
	void generateUrlForYahooTest() throws UrlShortenerException {
		String yahooUrl = "https://www.yahoo.com";
		String shortUrlGenerated = urlShortenerService.generateUrl(yahooUrl);

		Assert.assertTrue(shortUrlGenerated.length() == 7);
		Assert.assertTrue(UrlShortenerUtils.isAlphanumeric(shortUrlGenerated));
	}

	@Test
	void generateUrlForWhateverTest() throws UrlShortenerException {
		String whateverUrl = "https://www.whatever-stuff.com";
		String shortUrlGenerated = urlShortenerService.generateUrl(whateverUrl);

		Assert.assertTrue(shortUrlGenerated.length() == 15);
		Assert.assertTrue(UrlShortenerUtils.onlyContainsConsonants(shortUrlGenerated));
	}

	@Test
	void nullUrlTest() {
		Assertions.assertThrows(UrlShortenerException.class, () -> {
			urlShortenerService.generateUrl(null);
		});
	}

}
