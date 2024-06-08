package ui;

import dao.UsuarioDAO;
import entidades.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public MenuPrincipal() {
        setTitle("Sistema de Usuários");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu usuarioMenu = new JMenu("Usuario");
        menuBar.add(usuarioMenu);

        JMenuItem cadastrarUsuarioMenuItem = new JMenuItem("Cadastrar Usuario");
        usuarioMenu.add(cadastrarUsuarioMenuItem);

        JMenuItem consultarUsuarioMenuItem = new JMenuItem("Consultar Usuario");
        usuarioMenu.add(consultarUsuarioMenuItem);

        cadastrarUsuarioMenuItem.addActionListener(e -> abrirTelaCadastro());
        consultarUsuarioMenuItem.addActionListener(e -> abrirTelaConsulta());

        // Placeholder
        JLabel label = new JLabel("Bem-vindo ao Sistema de Usuários!", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }

    private void abrirTelaCadastro() {
        JFrame frame = new JFrame("Cadastrar Usuario");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();
        JLabel nivelLabel = new JLabel("Nivel:");
        JTextField nivelField = new JTextField();
        JLabel setorLabel = new JLabel("Setor:");
        JTextField setorField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JButton salvarButton = new JButton("Salvar");

        frame.add(nomeLabel);
        frame.add(nomeField);
        frame.add(nivelLabel);
        frame.add(nivelField);
        frame.add(setorLabel);
        frame.add(setorField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(new JLabel()); // Empty label for alignment
        frame.add(salvarButton);

        salvarButton.addActionListener(e -> {
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(usuarioDAO.consultarUsuarios().size() + 1);  // Simulação de auto incremento
            usuario.setNome(nomeField.getText());
            usuario.setNivel(Integer.parseInt(nivelField.getText()));
            usuario.setSetor(setorField.getText());
            usuario.setEmail(emailField.getText());

            usuarioDAO.cadastrarUsuario(usuario);
            frame.dispose();
        });

        frame.setVisible(true);
    }

    private void abrirTelaConsulta() {
        JFrame frame = new JFrame("Consultar Usuario");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JList<Usuario> usuarioJList = new JList<>(usuarioDAO.consultarUsuarios().toArray(new Usuario[0]));
        frame.add(new JScrollPane(usuarioJList), BorderLayout.CENTER);

        JButton excluirButton = new JButton("Excluir");
        frame.add(excluirButton, BorderLayout.SOUTH);

        excluirButton.addActionListener(e -> {
            Usuario usuario = usuarioJList.getSelectedValue();
            if (usuario != null) {
                usuarioDAO.removeUsuarioId(usuario.getUsuarioId());
                usuarioJList.setListData(usuarioDAO.consultarUsuarios().toArray(new Usuario[0]));
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            menuPrincipal.setVisible(true);
        });
    }
}
