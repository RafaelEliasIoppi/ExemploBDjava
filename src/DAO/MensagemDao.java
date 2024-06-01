package DAO;

import conector.Conexao;
import entidades.Mensagem;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MensagemDao {

    public void cadastrarMensagem(Mensagem mensagem) {
        String sql = "INSERT INTO mensagem (nome, remetente, destinatario, mensagem, data_envio, status) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);
            ps.setString(1, mensagem.getNome());
            ps.setString(2, mensagem.getRemetente());
            ps.setString(3, mensagem.getDestinatario());
            ps.setString(4, mensagem.getMensagem());
            ps.setDate(5, new java.sql.Date(mensagem.getDataEnvio().getTime()));
            ps.setString(6, mensagem.getStatus());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir mensagem: " + e.getMessage());
        }
    }
}