package com.unir.exampledfc.HotChiliLibrary.service;

import com.unir.exampledfc.HotChiliLibrary.dto.Author.AuthorCreateDTO;
import com.unir.exampledfc.HotChiliLibrary.dto.Author.AuthorDTO;
import com.unir.exampledfc.HotChiliLibrary.dto.Author.AuthorUpdateDTO;
import com.unir.exampledfc.HotChiliLibrary.entity.Author;
import com.unir.exampledfc.HotChiliLibrary.repository.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> findAll() {
        return authorRepository.findAll().stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName()))
                .collect(Collectors.toList());
    }

    public ResponseEntity<AuthorDTO> findById(Long id) {
        return authorRepository.findById(id)
                .map(author -> new AuthorDTO(author.getId(), author.getName()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public AuthorDTO save(AuthorCreateDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        Author savedAuthor = authorRepository.save(author);
        return new AuthorDTO(savedAuthor.getId(), savedAuthor.getName());
    }

    public AuthorDTO update(Long id, AuthorUpdateDTO authorUpdateDTO) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setName(authorUpdateDTO.getName());
                    return authorRepository.save(author);
                })
                .map(updatedAuthor -> new AuthorDTO(updatedAuthor.getId(), updatedAuthor.getName()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()).getBody();
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
