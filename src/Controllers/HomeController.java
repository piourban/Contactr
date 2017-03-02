package Controllers;

import Models.Contact;
import Services.ContactService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Piotr Urban on 23.02.2017.
 */

// Kontroler obslugujacy strone glowna
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        displayContacts(req);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    // Metoda zapisujac liste istniejacych kontaktow do atrybutu.
    private void displayContacts(HttpServletRequest request) {

        ContactService contactService = new ContactService();
        List<Contact> contactsList = contactService.getAllContact();
        request.setAttribute("contacts", contactsList);
    }
}
