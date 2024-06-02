package conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/exemplobd";
    private static final String user = "root";
    private static final String password = "1234";

    private static Connection conexao;

    public static synchronized Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            return null;
        }
        return conexao;
    }
    
    public static PreparedStatement getPreparedStatement(String sql, int returnType) {
        try {
            Connection con = getConexao();
            if (con != null) {
                return con.prepareStatement(sql, returnType);
            } else {
                throw new SQLException("Conexão é nula, não foi possível criar o PreparedStatement.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar o PreparedStatement: " + e.getMessage(), e);
        }
    }
}