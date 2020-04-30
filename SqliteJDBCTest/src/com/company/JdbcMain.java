import com.company.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class JdbcMain {
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO: Load the SQLite JDBC driver (JDBC class implements java.sql.Driver)
        Class.forName("org.sqlite.JDBC");

        // TODO: Create a DB connection
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:contactmgr.db")) {

            // TODO: Create a SQL statement
            Statement statement = connection.createStatement();

            // TODO: Create a DB table
            statement.executeUpdate("drop table if exists contacts");
            statement.executeUpdate("create table contacts (id integer primary key, firstname string, lastname string, email string, phone int(10))");

            // TODO: Insert a couple contacts
//            statement.executeUpdate("insert into contacts (firstname, lastname, email, phone) values ('qi', 'mu', 'qmu@bu.edu', 1234561111)");
//            statement.executeUpdate("insert into contacts (firstname, lastname, email, phone) values ('steve', 'md', 'qmu@bu2.edu', 1234561111)");
            Contact c = new Contact("chris", "mu", "wer@s.com", 123);
            save(c, statement);

            c = new Contact("chris12312", "m12312u", "wer@s12312.com", 123L);
            save(c, statement);

            // TODO: Fetch all the records from the contacts table
            ResultSet rs = statement.executeQuery("select * from contacts");

            // TODO: Iterate over the ResultSet & display contact info
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");

                System.out.printf("%s %s %d\n", firstName, lastName, id);
            }

        } catch (SQLException ex) {
            // Display connection or query errors
            System.err.printf("There was a database error: %s%n", ex.getMessage());
        }
    }

    public static void save(Contact contact, Statement statement) throws SQLException {
        // compose query
        String sql = "insert into contacts (firstname, lastname, email, phone) values ('%s', '%s','%s', %d)";
        sql = String.format(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());

        // exe the query
        statement.executeUpdate(sql);

    }
}