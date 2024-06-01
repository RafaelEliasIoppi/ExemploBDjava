package DAO;

import conector.Conexao;
import entidades.Usuario;
import java.sql.PreparedStatement;

public class UsuarioDao {

    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, login, senha, email) VALUES (?,?,?,?)";

        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println("Erro ao inserir usuario: " + e.getMessage());
        }
    }
}