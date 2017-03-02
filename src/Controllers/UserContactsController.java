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
import java.util.List;

/**
 * Created by Piotr Urban on 28.02.2017.
 */

//Kontroler odpowiedzialny za wyswietlanie wszystkich kontaktow danego uzytkownika
@WebServlet ("/myContacts")
public class UserContactsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        displayUserNotes(req);
        req.getRequestDispatcher("/WEB-INF/views/userContacts.jsp").forward(req,resp);
    }

    // Pobranie uzytkownika na podstawie sesji i za pomoca jego ID zapisane listy kontaktow do atrybutu
    private void displayUserNotes(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        long ID = user.getId();

        ContactService contactService = new ContactService();
        List<Contact> contacts = contactService.getUserContacts(ID);
        request.setAttribute("contacts", contacts);

    }
}
