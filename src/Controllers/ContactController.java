package Controllers;

import Models.User;
import Services.ContactService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static Models.Contact.getCategoryList;
import static Models.Contact.getSubcategoryList;
import static Services.ContactService.setSubcategory;

/**
 * Created by Piotr Urban on 25.02.2017.
 */

// Kontroler obslugujacy dodawanie nowych kontaktów
@WebServlet("/add")
public class ContactController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getUserPrincipal() != null) {

            req.setAttribute("categories", getCategoryList());
            req.setAttribute("subcategories", getSubcategoryList());
            req.getRequestDispatcher("WEB-INF/views/new.jsp").forward(req, resp);
        } else {
            resp.sendError(403);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("inputName");
        String surname = req.getParameter("inputSurname");
        String email = req.getParameter("inputEmail");
        String category = req.getParameter("inputCategory");
        String subcategory = setSubcategory(req);
        String phone = req.getParameter("inputPhone");
        Date birthday = Date.valueOf(req.getParameter("inputBirthday"));

        User user = (User) req.getSession().getAttribute("user");

        if (req.getUserPrincipal() != null) {
            ContactService contactService = new ContactService();
            contactService.add(name, surname, email, category, subcategory, phone, birthday, user);
            resp.sendRedirect("/home");
        } else {
            resp.sendError(403);
        }
    }

}
