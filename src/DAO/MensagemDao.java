package DAO;

import conector.Conexao;
import entidades.Mensagem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MensagemDao {

    public int cadastrarMensagem(Mensagem mensagem) {
        String sql = "INSERT INTO mensagem (nome, setor, destinatario, mensagem, data_envio, status) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, mensagem.getNome());
            ps.setString(2, mensagem.getSetor());
            ps.setString(3, mensagem.getDestinatario());
            ps.setString(4, mensagem.getMensagem());
            ps.setString(5, mensagem.getDataEnvio());
            ps.setString(6, mensagem.getStatus());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }

            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir mensagem: " + e.getMessage());
        }
        return -1; // Retorna -1 se ocorrer um erro
    }

    public List<Mensagem> consultarMensagensPorNomeData(String nome, String dataEnvio) {
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "SELECT * FROM mensagem WHERE nome = ? AND data_envio = ?";

        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, dataEnvio);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mensagem mensagem = extractMensagemFromResultSet(rs);
                mensagens.add(mensagem);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao consultar mensagens: " + e.getMessage());
        }

        return mensagens;
    }

    public List<Mensagem> consultarTodasMensagens() {
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "SELECT * FROM mensagem";

        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mensagem mensagem = extractMensagemFromResultSet(rs);
                mensagens.add(mensagem);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao consultar mensagens: " + e.getMessage());
        }

        return mensagens;
    }

    public List<Mensagem> consultarMensagensPorSetorData(String setor, String dataEnvio) {
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "SELECT * FROM mensagem WHERE setor = ? AND data_envio = ?";

        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);
            ps.setString(1, setor);
            ps.setString(2, dataEnvio);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mensagem mensagem = extractMensagemFromResultSet(rs);
                mensagens.add(mensagem);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao consultar mensagens por setor e data: " + e.getMessage());
        }

        return mensagens;
    }

    public List<Mensagem> consultarMensagensPorNomeSetorData(String nome, String setor, String dataEnvio) {
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "SELECT * FROM mensagem WHERE nome = ? AND setor = ? AND data_envio = ?";

        try {
            PreparedStatement ps = Conexao.getPreparedStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, setor);
            ps.setString(3, dataEnvio);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mensagem mensagem = extractMensagemFromResultSet(rs);
                mensagens.add(mensagem);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao consultar mensagens por nome, setor e data: " + e.getMessage());
        }

        return mensagens;
    }

    private Mensagem extractMensagemFromResultSet(ResultSet rs) throws SQLException {
        Mensagem mensagem = new Mensagem();
        mensagem.setCodigo(rs.getInt("msg_id"));
        mensagem.setNome(rs.getString("nome"));
        mensagem.setSetor(rs.getString("setor"));
        mensagem.setDestinatario(rs.getString("destinatario"));
        mensagem.setMensagem(rs.getString("mensagem"));
        mensagem.setDataEnvio(rs.getString("data_envio"));
        mensagem.setStatus(rs.getString("status"));
        return mensagem;
    }
}