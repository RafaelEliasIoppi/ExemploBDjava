import DAO.UsuarioDao;
import entidades.Usuario;
public class App {
    public static void main(String[] args) throws Exception {
        
        Usuario usuario = new Usuario("Rafael", "rafael", "1234", "rafael@gmail");

        new UsuarioDao().cadastrarUsuario(usuario);
    
    }
}
