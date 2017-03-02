package Controllers;

import Services.ContactService;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Piotr Urban on 28.02.2017.
 */

// Kontroler obslugujacy usuwanie komentarza na podstawie przekazanego ID
@WebServlet ("/delete")
public class DeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        deleteContact(req);
        resp.sendRedirect("/myContacts");
    }

    // Metoda pobierajaca ID kontaktu z przekazanego parametru i przekazujaca go do metody deleteContact() z serwisu.
    private boolean deleteContact (HttpServletRequest request){

        long ID = Long.parseLong(request.getParameter("contact_id"));
        boolean result = false;

        ContactService contactService = new ContactService();
        result = contactService.deleteContact(ID);

        return result;
    }
}
