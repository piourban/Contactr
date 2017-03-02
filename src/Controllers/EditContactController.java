package Controllers;

import Models.Contact;
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
 * Created by Piotr Urban on 28.02.2017.
 */

// Kontroler obslugujacy edycje kontaktów
@WebServlet ("/edit")
public class EditContactController extends HttpServlet {

    //Pobranie z parametru ID kontaktu do edycji, wywolanie metody z serwisu zwracajacej obiekt na podstawie ID i przekazanie go w postaci atrybutu
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long ID = Long.parseLong(req.getParameter("contact_id"));
        req.setAttribute("categories", getCategoryList());
        req.setAttribute("subcategories", getSubcategoryList());

        ContactService contactService = new ContactService();
        Contact contact = contactService.getContactByID(ID);

        req.setAttribute("contact",contact);
        req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req,resp);
    }

    // Pobranie danych z formularza, utworenie nowego obiektu i zapisanie zmian w bazie za pomoca metody z serwisu i pobranego nr ID
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        long ID = Long.parseLong(req.getParameter("inputID"));
        String name = req.getParameter("inputName");
        String surname = req.getParameter("inputSurname");
        String email = req.getParameter("inputEmail");
        String category = req.getParameter("inputCategory");
        String subcategory = setSubcategory(req);
        String phone = req.getParameter("inputPhone");
        Date birthday = Date.valueOf(req.getParameter("inputBirthday"));

        User user = (User) req.getSession().getAttribute("user");
        Contact updatedContact = new Contact(ID, name, surname, email, category, subcategory, phone, user, birthday);

        if (req.getUserPrincipal() != null) {

            ContactService contactService = new ContactService();
            contactService.updateContact(updatedContact);
            resp.sendRedirect("/myContacts");
        } else {
            resp.sendError(403);
        }

    }
}
