package com.adrien;

import java.util.Optional;

import com.adrien.dao.InstrutorDAO;
import com.adrien.db.DBFactory;
import com.adrien.entities.Instrutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        InstrutorDAO instrutorDAO = new InstrutorDAO();

        Instrutor novoInstrutor = new Instrutor();
        novoInstrutor.setNome("Carlos Eduardo");
        novoInstrutor.setEmail("carlos.eduardo@example.com");
        novoInstrutor.setBiografia("Especialista em desenvolvimento de software e arquitetura de sistemas.");

        instrutorDAO.salvar(novoInstrutor);

        Optional<Instrutor> instrutorEncontrado = instrutorDAO.buscarPorId(novoInstrutor.getId());
        if (instrutorEncontrado.isPresent()) {
            System.out.println("Instrutor encontrado: " + instrutorEncontrado.get().getNome());
        } else {
            System.out.println("Instrutor não encontrado.");
        }

        novoInstrutor.setNome("Carlos E. Silva");
        instrutorDAO.atualizar(novoInstrutor);

        instrutorDAO.remover(novoInstrutor.getId());

        DBFactory.fechar();
    }
}