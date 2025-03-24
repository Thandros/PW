package br.cefetmg.guitar_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.cefetmg.guitar_store.model.Guitar;

@Repository
public interface GuitarRepository extends JpaRepository<Guitar, Long> {
    List<Guitar> findByNomeContaining(String name);
}
