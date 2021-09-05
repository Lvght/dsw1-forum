package br.ufscar.dsw1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class GenericDAO {

    /**
     * @return Uma conexão com o banco de dados.
     * @throws RuntimeException - Ocorreu um erro ao tentar inicializar a classe.
     * @throws SQLException     - Ocorreu um erro ao tentar se conectar ao banco de dados.
     */
    public static Connection getConnection() throws SQLException {
        // Inicializa a classe
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao inicializar o Driver do Postgres");
        }

        return DriverManager.getConnection("jdbc:" + System.getenv("DEBATR_DATABASE_URI"),
                System.getenv("DEBATR_DATABASE_USER"),
                System.getenv("DEBATR_DATABASE_PASSWORD"));
    }
}
