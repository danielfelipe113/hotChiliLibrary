package com.unir.exampledfc.HotChiliLibrary.controller;

import com.unir.exampledfc.HotChiliLibrary.dto.Book.Book;
import com.unir.exampledfc.HotChiliLibrary.service.RentService;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/books")
public class BookController {
    private final RentService rentService;
    private final RestTemplate restTemplate;

    public BookController(RentService rentService, RestTemplate restTemplate) {
        this.rentService = rentService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllRents(@RequestParam boolean rentedOnly) {
        String url = "http://search/book";
        // figure out if book exists
        ResponseEntity<List<Book>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        List<Book> books = response.getBody();

        if(books == null) {
            throw new OpenApiResourceNotFoundException("Books not found");
        }

        books.forEach(book -> book.setRented(rentService.isRentedById(book.getId())));

        Stream<Book> booksStream = books.stream();
        if (rentedOnly) {
            booksStream = booksStream.filter(Book::isRented);
        }

        return ResponseEntity.ok(booksStream.collect(Collectors.toList()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam Map<String, Object> searchCriteria) {
        boolean rentedOnly = false;
        if(searchCriteria.get("rentedOnly") != null) {
            rentedOnly = searchCriteria.get("rentedOnly").equals("true");
            searchCriteria.remove("rentedOnly");
        }

        String baseUrl = "http://search/book/search";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        searchCriteria.forEach(builder::queryParam);
        URI uri = builder.build().encode().toUri();
        // figure out if book exists
        ResponseEntity<List<Book>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        List<Book> books = response.getBody();

        if(books == null) {
            throw new OpenApiResourceNotFoundException("Books not found");
        }


        books.forEach(book -> book.setRented(rentService.isRentedById(book.getId())));

        Stream<Book> booksStream = books.stream();
        if (rentedOnly) {
            booksStream = booksStream.filter(Book::isRented);
        }

        return ResponseEntity.ok(booksStream.collect(Collectors.toList()));
    }

}
