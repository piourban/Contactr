package Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Piotr Urban on 23.02.2017.
 */

// Kontroler sprawdzajacy czy uzytkownik jest zlogowany, jestli tak, to nastepuje przekierowanie do strony glownej
@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getUserPrincipal() != null){
            resp.sendRedirect("/home");
        }else {
            resp.sendError(403);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
