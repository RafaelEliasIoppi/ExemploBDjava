import DAO.MensagemDao;
import entidades.Mensagem;
import java.util.Date;
import javax.swing.*;

public class Menu {
    public static void main(String[] args) {
        try {
            String nome = JOptionPane.showInputDialog(null, "Digite o nome:", "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);
            String remetente = JOptionPane.showInputDialog(null, "Digite o remetente:", "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);
            String destinatario = JOptionPane.showInputDialog(null, "Digite o destinatário:", "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);

            JTextArea mensagemArea = new JTextArea(10, 30); // Definindo tamanho da JTextArea
            mensagemArea.setLineWrap(true); // Quebrar linha automaticamente
            mensagemArea.setWrapStyleWord(true); // Quebrar palavras longas
            JScrollPane scrollPane = new JScrollPane(mensagemArea); // Adicionar JTextArea em um JScrollPane

            int option = JOptionPane.showOptionDialog(null, scrollPane, "Digite a mensagem:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (option == JOptionPane.OK_OPTION) {
                String mensagemTexto = mensagemArea.getText();

                String dataEnvioString = JOptionPane.showInputDialog(null, "Digite a data de envio (yyyy-mm-dd):", "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);
                Date dataEnvio = java.sql.Date.valueOf(dataEnvioString);

                String status = JOptionPane.showInputDialog(null, "Digite o status:", "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);

                Mensagem mensagem = new Mensagem(nome, remetente, destinatario, mensagemTexto, dataEnvio, status);

                MensagemDao mensagemDao = new MensagemDao();
                mensagemDao.cadastrarMensagem(mensagem);

                JOptionPane.showMessageDialog(null, "Mensagem salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar mensagem: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}