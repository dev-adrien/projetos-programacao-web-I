package com.adrien.dao;

import java.util.List;
import java.util.Optional;

import com.adrien.db.DBFactory;
import com.adrien.entities.Instrutor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class InstrutorDAO implements GenericDao<Instrutor, Long> {

  @Override
  public Instrutor salvar(Instrutor entidade) {
    EntityManager em = DBFactory.criarEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      em.persist(entidade);
      tx.commit();
      return entidade;
    } catch (Exception e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
  }

  @Override
  public Optional<Instrutor> buscarPorId(Long id) {
    EntityManager em = DBFactory.criarEntityManager();
    try {
      Instrutor instrutor = em.find(Instrutor.class, id);
      return Optional.ofNullable(instrutor);
    } catch (Exception e) {
      throw e;
    } finally {
      em.close();
    }
  }

  public Optional<Instrutor> buscarPorIdComCursos(Long id) {
    EntityManager em = DBFactory.criarEntityManager();
    try {
      return em.createQuery(
              "SELECT DISTINCT i FROM Instrutor i LEFT JOIN FETCH i.cursos WHERE i.id = :id",
              Instrutor.class)
          .setParameter("id", id)
          .getResultStream()
          .findFirst();
    } catch (Exception e) {
      throw e;
    } finally {
      em.close();
    }
  }

  @Override
  public List<Instrutor> buscarTodos() {
    EntityManager em = DBFactory.criarEntityManager();
    try {
      return em.createQuery("SELECT i FROM Instrutor i", Instrutor.class).getResultList();
    } catch (Exception e) {
      throw e;
    } finally {
      em.close();
    }
  }

  @Override
  public Instrutor atualizar(Instrutor entidade) {
    EntityManager em = DBFactory.criarEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      Instrutor instrutorAtualizado = em.merge(entidade);
      tx.commit();
      return instrutorAtualizado;
    } catch (Exception e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
  }

  @Override
  public void remover(Long id) {
    EntityManager em = DBFactory.criarEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();

      Instrutor instrutor = em.find(Instrutor.class, id);
      if (instrutor != null) {
        em.remove(instrutor);
      }
      
      tx.commit();
    } catch (Exception e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
  }

}
