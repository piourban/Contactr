package Filter;

import Models.User;
import Services.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Piotr Urban on 30.01.2017.
 */

// Filtr zawierajacy mapowanie, ktore sprawia, ze metoda doFilter() wykona sie przed przejsciem do jakiejkolwiek strony
@WebFilter("/*")
public class LoginFilter implements Filter {

    // Sprawdzenie czy uzytkownik jest zalogowany oraz czy nie jest zapisany obiekt user na poziomie sesji. JEsli nie nastepuje wywolanie metody saveUserInSession
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        if(httpReq.getUserPrincipal() != null && httpReq.getSession().getAttribute("user") == null) {
            saveUserInSession(httpReq);
        }
        chain.doFilter(request, response);
    }

    // Metoda zapisujaca uztkownika, pobranego z bazy na podstawie username'a, na poziomie sesji i zapisanie go w postaci atrybutu.
    private void saveUserInSession(HttpServletRequest request) {
        UserService userService = new UserService();
        String username = request.getUserPrincipal().getName();
        User userByUsername = userService.getUserByUsername(username);
        request.getSession(true).setAttribute("user", userByUsername);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}

