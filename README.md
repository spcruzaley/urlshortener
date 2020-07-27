# URL Shortener

SpringBoot application to **generate a short URL**.

### Installation

Download the repository.

```sh
$ git clone https://github.com/spcruzaley/urlshortener.git
```

Go to the repository folder and execute the following command in order to start the REST service.

```sh
$ cd urlshortener
$ mvn spring-boot:run
```

As soon as the application start, you can call the two endpoints generated (*one for create a short url, the other one to get the original url for a given short url*):

#### Generate a short url

Use a **POST** call given the url in json format as *url: http(s)://www.some-url.com*, for example:

```sh
$ curl -XPOST -d '{ "url": "http://www.google.com" }' -H "Content-type: application/json" http://localhost:8080/
```

You should to receive a response like this:

```sh
$ {"alias":"FRowa","url":"http://www.google.com","errorMessage":null}
```

#### Get an entry url for a given short url

Use a **GET** call to get an entry url base on the short url given, for example:

```sh
$ curl http://localhost:8080/FRowa
```

You should to receive a response like this:

```sh
$ {"alias":"FRowa","url":"http://www.google.com","errorMessage":null}
```

### Aditional Info

The application uses **Cache** (*https://spring.io/guides/gs/caching/*) functionality to store the short url generates in memory, this will to allow us to get a short url faster and avoid to generate again the same url given.
