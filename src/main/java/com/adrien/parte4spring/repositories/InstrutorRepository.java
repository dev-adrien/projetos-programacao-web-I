package com.adrien.parte4spring.repositories;


import com.adrien.parte4spring.entities.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
    List<Instrutor> findByNomeContaining(String nome);
}

