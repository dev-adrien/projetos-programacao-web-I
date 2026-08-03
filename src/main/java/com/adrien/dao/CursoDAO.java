package com.adrien.dao;

import java.util.List;
import java.util.Optional;

import com.adrien.db.DBFactory;
import com.adrien.entities.Curso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CursoDAO implements GenericDao<Curso, Long> {

  @Override
  public Curso salvar(Curso entidade) {
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
  public Optional<Curso> buscarPorId(Long id) {
    EntityManager em = DBFactory.criarEntityManager();
    try {
      Curso curso = em.find(Curso.class, id);
      return Optional.ofNullable(curso);
    } catch (Exception e) {
      throw e;
    } finally {
      em.close();
    }
  }

  @Override
  public List<Curso> buscarTodos() {
    EntityManager em = DBFactory.criarEntityManager();
    try {
      return em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
    } catch (Exception e) {
      throw e;
    } finally {
      em.close();
    }
  }

  @Override
  public Curso atualizar(Curso entidade) {
    EntityManager em = DBFactory.criarEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      Curso cursoAtualizado = em.merge(entidade);
      tx.commit();
      return cursoAtualizado;
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

      Curso curso = em.find(Curso.class, id);
      if (curso != null) {
        em.remove(curso);
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
