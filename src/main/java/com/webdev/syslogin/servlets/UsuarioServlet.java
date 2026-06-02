package com.webdev.syslogin.servlets;

import com.webdev.syslogin.model.Usuario;
import com.webdev.syslogin.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet ("/users")
public class UsuarioServlet extends HttpServlet {

    private final UsuarioService service = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String passWord = req.getParameter("passWord");

        try {

            service.cadastrar(userName, email, passWord);

            resp.setStatus(HttpServletResponse.SC_CREATED); // http 201
            resp.sendRedirect("users");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // erro 400
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("erro.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("users", UsuarioService.getUsuarios());
        req.getRequestDispatcher("listar.jsp").forward(req, resp);


    }
}
