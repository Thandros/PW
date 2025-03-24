package br.cefetmg.guitar_store.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cefetmg.guitar_store.model.Guitar;
import br.cefetmg.guitar_store.service.GuitarService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/guitar")
public class GuitarController {
    
    @Autowired
    private final GuitarService guitarService;

    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Guitar> create(@Valid @RequestBody Guitar guitar) {
        guitar = guitarService.create(guitar);
        return ResponseEntity.ok().body(guitar);
    }

    @PutMapping({"", "/"})
    public ResponseEntity<Guitar> update(@Valid @RequestBody Guitar guitar) {
        guitar = guitarService.update(guitar);
        return ResponseEntity.ok().body(guitar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Guitar> delete(@PathVariable Long id) {
        Guitar guitar = guitarService.delete(id);
        return ResponseEntity.ok().body(guitar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guitar> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(guitarService.getById(id));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Guitar>> getAll() {
        return ResponseEntity.ok().body(guitarService.getAll());
    }

    @GetMapping("/searchText/{searchText}")
    public ResponseEntity<List<Guitar>> searchByText(@PathVariable String searchText) {
        return ResponseEntity.ok().body(guitarService.getBySearchText(searchText));
    }

    @GetMapping({"searchText", "/searchText/"})
    public ResponseEntity<List<Guitar>> searchByText() {
        return ResponseEntity.ok().body(new ArrayList<>());
    }
}
