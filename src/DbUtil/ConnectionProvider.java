package DbUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Piotr Urban on 23.02.2017.
 */

//Klasa umozliwiajaca pobranie zrodla danych skonfigurowanego w pliku context.xml
public class ConnectionProvider {

    private static DataSource dataSource;

    public static Connection geConnection() throws SQLException {

        return getDataSource().getConnection();
    }

    // Metoda zwracajaca zrodlo danych, pod warunkiem, ze wczesniej nie zostalo zainicjowane.
    public static DataSource getDataSource() {

        if (dataSource == null) {

            try {
                Context initialContext = new InitialContext();
                Context envContext = (Context) initialContext.lookup("java:comp/env");

                DataSource ds = (DataSource) envContext.lookup("jdbc/contactr-basic");
                dataSource = ds;
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }
}
