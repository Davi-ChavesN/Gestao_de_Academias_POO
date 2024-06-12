package data_base_connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public Connection getConnection()
    {
        try
        {
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "root");
            properties.setProperty("useSSL", "false");
            properties.setProperty("useTimezone", "true");
            properties.setProperty("serverTimezone", "America/Sao_Paulo");
            properties.setProperty("allowPublicKeyRetrieval", "true");

            String con = "jdbc:mysql://localhost/gestao_de_academias";
            return DriverManager.getConnection(con, properties);
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
