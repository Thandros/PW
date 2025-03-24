package br.cefetmg.guitar_store.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.cefetmg.guitar_store.model.Guitar;
import br.cefetmg.guitar_store.repository.GuitarRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuitarService {
    private final GuitarRepository guitarRepository;

    public Guitar create(Guitar guitar) {
        if (guitar != null && guitar.getId() != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guitar ID must be null to create a new Guitar");
        }
        guitar = guitarRepository.save(guitar);
        return guitar;
    }

    public Guitar update(Guitar guitar) {
        if (guitar != null && guitar.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Guitar ID cannot be null");
        }
        guitar = guitarRepository.save(guitar);
        return guitar;
    }

    public Guitar delete(Long id) {
        Guitar guitar = getById(id);
        if (guitar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guitar not found");
        }
        guitarRepository.delete(guitar);
        return guitar;
    }

    public Guitar getById(Long id) {
        Guitar guitar = guitarRepository.findById(id).orElse(null);
        if (guitar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guitar not found");
        }
        return guitar;
    }

    public List<Guitar> getAll() {
        return guitarRepository.findAll();
    }
    
    public List<Guitar> getBySearchText(String searchText) {
        List<Guitar> guitarList = guitarRepository.findByNomeContaining(searchText);
        if (guitarList == null) {
            guitarList = new ArrayList<>();
        }
        return guitarList;
    }
}
