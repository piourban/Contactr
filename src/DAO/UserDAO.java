package DAO;

import Models.User;

import java.util.List;

/**
 * Created by Piotr Urban on 23.02.2017.
 */

// Interfejs deklarujacy metody CRUD'owe dziedziczac z interfejsu GenericDAO i dodatkowe zwracajace wszystkich uzytkownikow oraz uzytkownika na podstawie username'a
public interface UserDAO extends GenericDAO<User, Long> {

    List<User> getAll();
    User getUserByUsername(String username);

}
