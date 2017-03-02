package DAO;

import Models.Contact;

import java.util.List;

/**
 * Created by Piotr Urban on 25.02.2017.
 */

// Interfejs deklarujacy metody CRUD'owe dziedziczac z interfejsu GenericDAO i dodatkowe zwracajace wszystkie kontakty, zarowno ogolne jak i na podstawie ID uzytkownika
public interface ContactDAO extends GenericDAO<Contact, Long> {

    List<Contact> getAll();

    List<Contact> getContactByUserID (long ID);

}
