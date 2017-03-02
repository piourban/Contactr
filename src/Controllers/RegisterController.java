package Controllers;

import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Piotr Urban on 23.02.2017.
 */

//Kontroler obsugujacy rejestracje uzytkownika
@WebServlet ("/register")
public class RegisterController extends HttpServlet {

    // przekierowanie do strony register.jsp
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    //pobranie danych z formularza i utworzenie nowego kontaktu za pomoca metody sewrisu
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("inputUsername");
        String password = req.getParameter("inputPassword");
        String email = req.getParameter("inputEmail");

        UserService userService = new UserService();
        userService.addUser(username, email, password);
        resp.sendRedirect("/home");

    }
}
