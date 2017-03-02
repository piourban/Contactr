package DAO;

import DbUtil.ConnectionProvider;
import Models.Contact;
import Models.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Piotr Urban on 25.02.2017.
 */

// Klasa implementujaca metody interfejsu ContactDAO, obslugujaca dostep do danych z bazy obiektu kontakt
public class ContactDAOImpl implements ContactDAO {

    private static final String create = "insert into contact (user_id, name, surname, email, phone, birthday, category, subcategory) values (:user_id, :name, :surname, :email, :phone, :birthday, :category, :subcategory)";
    private static final String read = "select contact_id, user_id , name, surname, email, phone, birthday, category, subcategory, date from contact WHERE contact_id=:contact_id";
    private static final String updateContact = "update contact set name = :name, surname = :surname, email = :email, phone = :phone, birthday = :birthday, category = :category, subcategory = :subcategory where contact_id = :contact_id";
    private static final String deleteContactByID = "delete from contact where contact_id = :contact_id";
    private static final String readAll = "select user.user_id, username, user.email, isActive, password, contact_id, name, surname, contact.email, phone, birthday, category, subcategory, date from contact LEFT JOIN user ON contact.user_id=user.user_id";
    private static final String readByUserID = "select contact_id,user_id, name, surname, email, phone, birthday, category, subcategory, date from contact where user_id = :user_id";

    private NamedParameterJdbcTemplate template;

    public ContactDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    // Metoda tworzaca nowy kontakt na podstawie przekazanej mapy - klucz, wartosc
    @Override
    public Contact create(Contact contact) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String,Object> map = new HashMap<>();
        map.put("name", contact.getName());
        map.put("surname",contact.getSurname());
        map.put("email",contact.getEmail());
        map.put("phone",contact.getPhone());
        map.put("birthday", contact.getBirthday());
        map.put("category", contact.getCategory());
        map.put("subcategory", contact.getSubcategory());
        map.put("user_id", contact.getUser().getId());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(map);
        int rows = template.update(create, sqlParameterSource, keyHolder);

        if (rows > 0)
            contact.setId((Long) keyHolder.getKey());

        return contact;
    }

    // Odczytanie kontaktu na podstawie jego ID
    @Override
    public Contact read(Long primaryKey) {

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("contact_id", primaryKey);
        Contact contact = (Contact) template.queryForObject(read, sqlParameterSource, new UserContactRowMapper());

        return contact;
    }

    // Update istniejacego kontaktu
    @Override
    public boolean update(Contact contact) {

        boolean result = false;
        Map<String,Object> map = new HashMap<>();
        map.put("name", contact.getName());
        map.put("surname",contact.getSurname());
        map.put("email",contact.getEmail());
        map.put("phone",contact.getPhone());
        map.put("birthday", contact.getBirthday());
        map.put("contact_id", contact.getId());
        map.put("category", contact.getCategory());
        map.put("subcategory", contact.getSubcategory());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(map);
        int update = template.update(updateContact, sqlParameterSource);

        System.out.println(updateContact);

        if (update > 0) {
            result = true;
        }
        return result;
    }

    // Usuwanie kontaktu na podstawie przekazanego ID
    @Override
    public boolean delete(Long key) {

        boolean result = false;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("contact_id", key);
        int delete = template.update(deleteContactByID, sqlParameterSource);

        if (delete > 0) result = true;

        return result;
    }

    // Pobieranie listy wszystkich kontaktow
    @Override
    public List<Contact> getAll() {

        List<Contact> contactList = template.query(readAll, new ContactRowMapper());
        return contactList;
    }

    // Pobieranie listy kontaktow na podstawie ID uzytkownika
    @Override
    public List<Contact> getContactByUserID(long ID) {

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("user_id", ID);
        List<Contact> contactList = template.query(readByUserID, sqlParameterSource, new UserContactRowMapper());

        return contactList;
    }

    // Mapowanie obiektu na odpowiednie typy
    private class ContactRowMapper implements RowMapper {


        @Override
        public Contact mapRow(ResultSet resultSet, int i) throws SQLException {

            Contact contact = new Contact();
            contact.setId(resultSet.getLong("contact_id"));
            contact.setName(resultSet.getString("name"));
            contact.setSurname(resultSet.getString("surname"));
            contact.setEmail(resultSet.getString("email"));
            contact.setCategory(resultSet.getString("category"));
            contact.setSubcategory(resultSet.getString("subcategory"));
            contact.setBirthday(resultSet.getDate("birthday"));
            contact.setPhone(resultSet.getString("phone"));
            contact.setData(resultSet.getTimestamp("date"));

            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));

            contact.setUser(user);

            return contact;
        }
}
    // Mapowanie obiektu na odpowiednie typy
    private class UserContactRowMapper implements RowMapper {

        @Override
        public Contact mapRow(ResultSet resultSet, int i) throws SQLException {

            Contact contact = new Contact();
            contact.setId(resultSet.getLong("contact_id"));
            contact.setName(resultSet.getString("name"));
            contact.setSurname(resultSet.getString("surname"));
            contact.setEmail(resultSet.getString("email"));
            contact.setCategory(resultSet.getString("category"));
            contact.setSubcategory(resultSet.getString("subcategory"));
            contact.setBirthday(resultSet.getDate("birthday"));
            contact.setPhone(resultSet.getString("phone"));
            contact.setData(resultSet.getTimestamp("date"));

            return contact;
        }
    }
}
