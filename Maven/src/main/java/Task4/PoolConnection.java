package Task4;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class PoolConnection {
    public static void main(String[] args) {
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        try {
            cpds.setDriverClass("com.mysql.jdbc.Driver") ;

            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/dbName");
            cpds.setUser("dbLogin");
            cpds.setPassword("dbPassword");

            Properties properties = new Properties();
            properties.setProperty("user", "dbLogin");
            properties.setProperty("password", "dbPassword");
            properties.setProperty("useUnicode", "true");
            properties.setProperty("characterEncoding", "UTF8");
            cpds.setProperties(properties);

            // set options
            cpds.setMaxStatements(180);
            cpds.setMaxStatementsPerConnection(180);
            cpds.setMinPoolSize(50);
            cpds.setAcquireIncrement(10);
            cpds.setMaxPoolSize(60);
            cpds.setMaxIdleTime(30);

            // Получить подключение из пула
            Connection connection = cpds.getConnection();
            System.out.println ("closeConnection : idleConnections = " + cpds.getNumIdleConnections() +
                    ", busyConnections = " + cpds.getNumBusyConnections());
            // "Вернуть" (закрыть) подключение
            connection.close();

            System.out.println ("closeConnection : idleConnections = " + cpds.getNumIdleConnections() +
                    ", busyConnections = " + cpds.getNumBusyConnections()); }
        catch (PropertyVetoException  | SQLException exception) {
            exception.printStackTrace();
        }

    }

}

