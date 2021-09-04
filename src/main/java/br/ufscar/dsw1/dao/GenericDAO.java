package br.ufscar.dsw1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class GenericDAO {

    /**
     * @return Connection
     * @throws ClassNotFoundException - Ocorreu um erro ao tentar inicializar a classe.
     * @throws SQLException           - Ocorreu um erro ao tentar se conectar ao banco de dados,
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Inicializa a classe
        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection("jdbc:" + System.getenv("DEBATR_DATABASE_URI"),
                System.getenv("DEBATR_DATABASE_USER"),
                System.getenv("DEBATR_DATABASE_PASSWORD"));
    }
}
