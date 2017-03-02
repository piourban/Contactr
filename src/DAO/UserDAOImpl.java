package DAO;


import DbUtil.ConnectionProvider;
import Models.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Piotr Urban on 23.02.2017.
 */

// Klasa implementujaca metody interfejsu UserDAO, obslugujaca dostep do danych z bazy obiektu user
public class UserDAOImpl implements UserDAO {

    private final static String create = "insert into user (username, password, email, isActive) values (:username, :password, :email, :active)";
    private final static String role = "INSERT INTO user_role(username) VALUES(:username)";
    private final static String read = "SELECT user_id, username, email, password, isActive FROM user WHERE user_id = :user_id";
    private final static String readByUsername = "select user_id,username, email, isActive, password from user where username = :username";

    private NamedParameterJdbcTemplate template;

    public UserDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    // Zapisanie nowego uzytkownika na podstawie przyjetego argumentu User, przechwycenie klucz bazy danych
    @Override
    public User create(User user) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        int rows = template.update(create, sqlParameterSource, keyHolder);

        if (rows > 0) {

            user.setId((Long) keyHolder.getKey());
            setPrivigiles(user);
        }
        return user;
    }

    //  Ustawienie dla danego uzytkownika roli w bazie
    private void setPrivigiles(User user) {

        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        template.update(role, sqlParameterSource);
        /*ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = ConnectionProvider.geConnection().prepareStatement(role);
            preparedStatement.setString(1,user.getUsername());
            System.out.println(user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    // Pobranie uzytkownika na podstawie podanego ID
    @Override
    public User read(Long primaryKey) {

        User resultUser;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", primaryKey);
        resultUser = template.queryForObject(read, sqlParameterSource, new UserRowMapper());

        return resultUser;
    }

    @Override
    public boolean update(User updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    // Pobranie z bazy obiektu user na podstawie username'a
    @Override
    public User getUserByUsername(String username) {

        User resultUser = null;
        SqlParameterSource paramSource = new MapSqlParameterSource("username", username);
        resultUser = template.queryForObject(readByUsername, paramSource, new UserRowMapper());

        return resultUser;
    }

    // Mapowanie obiektu na odpowiednie typy
    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));

            return user;
        }
    }
}
