package entidades;

import javax.swing.*;
import DAO.MensagemDao;
import entidades.Mensagem;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cadastro extends JFrame implements ActionListener {
    private JComboBox<String> nomeComboBox, setorComboBox, destinatarioComboBox;
    private JTextArea mensagemArea;
    private JButton submeterButton;

    private String[] nomes = {"Rafael", "Diego", "Tiago", "Gabriel", "Alexandre", "Michele", "Sabrina", "Milena"};
    private String[] setores = {"Microbiologia", "FQ", "ADM"};
    private String[] destinatarios = {"Diego", "Gabriel", "Alexandre", "Michele", "Sabrina", "Milena"};

    public Cadastro() {
        // Configurações da janela
        setTitle("Cadastro de Mensagem");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Painel principal com BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel);

        // Font for labels
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        // Adicionando campos e rótulos com ícones
        mainPanel.add(createLabelPanel("Nome:", "icons/name.png", labelFont));
        nomeComboBox = new JComboBox<>(nomes);
        nomeComboBox.setPreferredSize(new Dimension(300, 30));
        mainPanel.add(createFieldPanel(nomeComboBox));

        mainPanel.add(createLabelPanel("Setor:", "icons/setor.png", labelFont));
        setorComboBox = new JComboBox<>(setores);
        setorComboBox.setPreferredSize(new Dimension(300, 30));
        mainPanel.add(createFieldPanel(setorComboBox));

        mainPanel.add(createLabelPanel("Destinatário:", "icons/destinatario.png", labelFont));
        destinatarioComboBox = new JComboBox<>(destinatarios);
        destinatarioComboBox.setPreferredSize(new Dimension(300, 30));
        mainPanel.add(createFieldPanel(destinatarioComboBox));

        mainPanel.add(createLabelPanel("Mensagem:", "icons/message.png", labelFont));
        mensagemArea = new JTextArea(10, 50); // Aumentar significativamente o campo de mensagem
        JScrollPane scrollPane = new JScrollPane(mensagemArea);
        mainPanel.add(createFieldPanel(scrollPane));

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        submeterButton = new JButton("Submeter");
        submeterButton.addActionListener(this);
        buttonPanel.add(submeterButton);
        mainPanel.add(buttonPanel);

        setVisible(true);
    }

    private JPanel createLabelPanel(String labelText, String iconPath, Font font) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        label.setIcon(new ImageIcon(iconPath));
        panel.add(label);
        return panel;
    }

    private JPanel createFieldPanel(JComponent field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(field);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submeterButton) {
            try {
                String nome = (String) nomeComboBox.getSelectedItem();
                String setor = (String) setorComboBox.getSelectedItem();
                String destinatario = (String) destinatarioComboBox.getSelectedItem();
                String mensagemTexto = mensagemArea.getText();

                // Obtendo a data e hora atual
                Date dataEnvio = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dataString = sdf.format(dataEnvio);

                // Criação do objeto Mensagem
                Mensagem mensagem = new Mensagem(nome, setor, destinatario, mensagemTexto, dataString);

                // Chamada para o método de cadastro
                MensagemDao mensagemDao = new MensagemDao();
                mensagemDao.cadastrarMensagem(mensagem);

                JOptionPane.showMessageDialog(this, "Mensagem salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar mensagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new Cadastro();
    }
}