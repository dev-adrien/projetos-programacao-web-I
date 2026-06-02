package com.webdev.syslogin.service;

import com.webdev.syslogin.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class UsuarioService {
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final AtomicLong CONTADOR_ID = new AtomicLong(1);

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario cadastrar(String nome, String email, String senha) throws Exception {

        if (nome == null || nome.isEmpty() || email == null || email.isEmpty()) {
            throw new Exception("Erro: os campos não podem estar vazios!");
        }

        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                throw new Exception("Erro: email já cadastrado.");
            }
        }

        Usuario novoUsuario = new Usuario(nome, email, senha);

        long id = CONTADOR_ID.getAndIncrement();
        novoUsuario.setId(id);

        usuarios.add(novoUsuario);

        return novoUsuario;
    }
}