package com.unir.exampledfc.HotChiliLibrary.dto.Book;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;

    private String name;

    private int publishedYear;
    private String isbn10;
    private String isbn13;
    private Long authorId;
    private String image;

    private String synopsis;

    private List<Object> critics;

    private String genre;

    private boolean isRented = false;
}
