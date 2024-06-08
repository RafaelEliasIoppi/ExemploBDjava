package dao;

import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private List<Usuario> usuarios = new ArrayList<>();

    public void cadastrar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> consultarUsuarios() {
        return usuarios;
    }
    
    public Usuario consultarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuarioId() == id) {
                return usuario;
            }
        }0
        return null;
    }

    public void removeUsuarioId(int id) {
        Usuario usuario = consultarUsuarioPorId(id);
        if (usuario != null) {
            usuarios.remove(usuario);
        }
    }

}
