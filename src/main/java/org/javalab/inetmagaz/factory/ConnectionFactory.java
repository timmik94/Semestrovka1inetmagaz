package org.javalab.inetmagaz.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by timur on 10.04.2014.
 */
public class ConnectionFactory {
    private static ConnectionFactory instance;
    private String driver="org.apache.derby.jdbc.EmbeddedDriver";
    private String url="jdbc:derby:magazin;create=true";
    private Connection connection;
    private ConnectionFactory(){
        try {
            Class.forName(driver);
            connection= DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public Connection getconnection(){
        try {
            if (connection.isClosed()){
                Class.forName(driver);
              connection=DriverManager.getConnection(url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ConnectionFactory getInstance(){
        if(instance==null){
            instance=new ConnectionFactory();
        }
        return instance;

    }

}
