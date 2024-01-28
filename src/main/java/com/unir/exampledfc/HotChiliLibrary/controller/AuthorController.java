package com.unir.exampledfc.HotChiliLibrary.controller;

import com.unir.exampledfc.HotChiliLibrary.dto.Author.AuthorCreateDTO;
import com.unir.exampledfc.HotChiliLibrary.dto.Author.AuthorDTO;
import com.unir.exampledfc.HotChiliLibrary.dto.Author.AuthorUpdateDTO;
import com.unir.exampledfc.HotChiliLibrary.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorCreateDTO authorCreateDTO) {
        AuthorDTO savedAuthor = authorService.save(authorCreateDTO);
        return ResponseEntity.ok(savedAuthor);
    }

    @PutMapping("/{id}")
    public AuthorDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorUpdateDTO authorUpdateDTO) {
        return authorService.update(id, authorUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

