package entidades;

import javax.swing.*;
import DAO.MensagemDao;
import entidades.Mensagem;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Consultas extends JFrame implements ActionListener {
    private JComboBox<String> nomeComboBox, setorComboBox;
    private JTextField dataEnvioField;
    private JTextArea resultadoArea;
    private JButton consultarButton, consultarTodosButton;

    private String[] nomes = {"Todos", "Rafael", "Diego", "Tiago"};
    private String[] setores = { "Microbiologia", "FQ", "ADM"};

    public Consultas() {
        // Configurações da janela
        setTitle("Consulta de Mensagens");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel superior para filtros
        JPanel filtroPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        filtroPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        filtroPanel.add(new JLabel("Nome:"));
        nomeComboBox = new JComboBox<>(nomes);
        filtroPanel.add(nomeComboBox);

        filtroPanel.add(new JLabel("Setor:"));
        setorComboBox = new JComboBox<>(setores);
        filtroPanel.add(setorComboBox);

        filtroPanel.add(new JLabel("Data de Envio (yyyy-MM-dd):"));
        dataEnvioField = new JTextField();
        filtroPanel.add(dataEnvioField);

        consultarButton = new JButton("Consultar");
        consultarButton.addActionListener(this);
        filtroPanel.add(consultarButton);

        consultarTodosButton = new JButton("Consultar Todos");
        consultarTodosButton.addActionListener(this);
        filtroPanel.add(consultarTodosButton);

        add(filtroPanel, BorderLayout.NORTH);

        // Área de texto para exibir resultados
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(resultadoArea), BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MensagemDao mensagemDao = new MensagemDao();
        List<Mensagem> mensagens;

        if (e.getSource() == consultarButton) {
            String nome = (String) nomeComboBox.getSelectedItem();
            String setor = (String) setorComboBox.getSelectedItem();
            String dataEnvio = dataEnvioField.getText();

            if ("Todos".equals(nome) && "Todos".equals(setor)) {
                mensagens = mensagemDao.consultarTodasMensagens();
            } else if (!"Todos".equals(nome) && "Todos".equals(setor)) {
                mensagens = mensagemDao.consultarMensagensPorNomeData(nome, dataEnvio);
            } else if ("Todos".equals(nome) && !"Todos".equals(setor)) {
                mensagens = mensagemDao.consultarMensagensPorSetorData(setor, dataEnvio);
            } else {
                mensagens = mensagemDao.consultarMensagensPorNomeSetorData(nome, setor, dataEnvio);
            }

        } else if (e.getSource() == consultarTodosButton) {
            mensagens = mensagemDao.consultarTodasMensagens();
        } else {
            return;
        }

        // Exibição dos resultados
        resultadoArea.setText("");
        for (Mensagem mensagem : mensagens) {
            resultadoArea.append("ID: " + mensagem.getCodigo() + "\n");
            resultadoArea.append("Nome: " + mensagem.getNome() + "\n");
            resultadoArea.append("setor: " + mensagem.getSetor() + "\n");
            resultadoArea.append("Destinatário: " + mensagem.getDestinatario() + "\n");
            resultadoArea.append("Mensagem: " + mensagem.getMensagem() + "\n");
            resultadoArea.append("Data de Envio: " + mensagem.getDataEnvio() + "\n");
            resultadoArea.append("Status: " + mensagem.getStatus() + "\n");
            resultadoArea.append("-------------------------------------------------\n");
        }
    }

    public static void main(String[] args) {
        new Consultas();
    }
}