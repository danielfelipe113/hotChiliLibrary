package com.unir.exampledfc.HotChiliLibrary.service;


import com.unir.exampledfc.HotChiliLibrary.dto.Book.Book;
import com.unir.exampledfc.HotChiliLibrary.dto.Rent.RentCreateDTO;
import com.unir.exampledfc.HotChiliLibrary.dto.Rent.RentDTO;
import com.unir.exampledfc.HotChiliLibrary.dto.Rent.RentUpdateDTO;
import com.unir.exampledfc.HotChiliLibrary.entity.Rent;
import com.unir.exampledfc.HotChiliLibrary.entity.User;
import com.unir.exampledfc.HotChiliLibrary.repository.RentRepository;
import com.unir.exampledfc.HotChiliLibrary.repository.UserRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentService {
    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public RentService(RentRepository rentRepository, RestTemplate restTemplate, UserRepository userRepository) {
        this.rentRepository = rentRepository;
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    public List<RentDTO> findAll() {
        return rentRepository.findAll().stream()
                .map(rent -> new RentDTO(rent.getId(), rent.getUser(), rent.getBookId(), rent.getRentedDays(), rent.getRentalDate(), rent.getReturnDate()))
                .collect(Collectors.toList());
    }

    public ResponseEntity<RentDTO> findById(Long id) {
        return rentRepository.findById(id)
                .map(rent -> new RentDTO(rent.getId(), rent.getUser(), rent.getBookId(), rent.getRentedDays(), rent.getRentalDate(), rent.getReturnDate()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public RentDTO save(RentCreateDTO rentCreateDTO) {
        String url = String.format("http://search/book/%d", rentCreateDTO.getBookId());

        List<Rent> rentedBooks = rentRepository.findByBookId(rentCreateDTO.getBookId());
        if(!rentedBooks.isEmpty()) {
            // TODO Return in a better way
            throw new OpenApiResourceNotFoundException("Book is already rented");
        }
        // figure out if book exists
        ResponseEntity<Book> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        Book book = response.getBody();

        if(book == null) {
            throw new OpenApiResourceNotFoundException("Book not found with id: " + rentCreateDTO.getBookId());
        }

        // Find user
        Optional<User> user = userRepository.findById(rentCreateDTO.getUserId());

        if(user.isEmpty()) {
            throw new OpenApiResourceNotFoundException("User not found with id: " + rentCreateDTO.getUserId());
        }

        Rent rent = new Rent();
        rent.setBookId(book.getId());
        rent.setUser(user.get());
        rent.setRentedDays(rentCreateDTO.getDays());

        rent.setRentalDate(LocalDateTime.now());
        Rent savedRent = rentRepository.save(rent);
        return new RentDTO(
                savedRent.getId(),
                savedRent.getUser(),
                savedRent.getBookId(),
                savedRent.getRentedDays(),
                savedRent.getRentalDate(),
                savedRent.getReturnDate()
        );
    }


    public RentDTO update(Long id, RentUpdateDTO rentUpdateDTO) {
        Optional<Rent> rentFetch = rentRepository.findById(id);

        if(rentFetch.isEmpty()) {
            throw new OpenApiResourceNotFoundException("Rent not found with id: " + id);
        }

        Rent rentToUpdate = rentFetch.get();

        if(!rentToUpdate.getBookId().equals(rentUpdateDTO.getBookId())) {
            throw new OpenApiResourceNotFoundException("Rent not found with id: " + id);
        }

        rentToUpdate.setRentedDays(rentUpdateDTO.getDays());

        Rent rentSaved = rentRepository.save(rentToUpdate);

        return new RentDTO(rentSaved);
    }

    public List<Rent> findByBookId(Long bookId) {
        return rentRepository.findByBookId(bookId);
    }

    public boolean isRentedById(Long bookId) {
        List<Rent> rents = findByBookId(bookId);

        for (Rent rent : rents) {
            if (isRented(rent)) {
                return true;
            }
        }

        return false;
    }

    public void deleteById(Long id) {
        rentRepository.deleteById(id);
    }

    public boolean isRented(Rent rent) {
        return rent.getRentalDate() != null && rent.getReturnDate() == null;

    }
}
