package ru.javaops.web;

import com.google.common.collect.ImmutableMap;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import ru.javaops.masterjava.common.web.ThymeleafUtil;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class UsersServlet extends HttpServlet {
    private UserDao userDao = DBIProvider.getDao(UserDao.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final WebContext webContext = new WebContext(req, resp, req.getServletContext(), req.getLocale(),
                ImmutableMap.of("users", userDao.getWithLimit(20)));
        final TemplateEngine engine = ThymeleafUtil.getTemplateEngine(getServletContext());
        engine.process("users", webContext, resp.getWriter());
    }
}
