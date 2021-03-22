package servicios;

import dao.modelo.Usuario;
import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.util.List;

public interface ServiciosUsuarios {
    @SneakyThrows
    static String bytesToHex(String hash) {
        StringBuffer hexString = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("SHA3-512");
        byte[] hashBytes = md.digest(hash.getBytes());
        for (int i = 0; i < hashBytes.length; i++) {
            String hex = Integer.toHexString(0xff & hashBytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @SneakyThrows
    Usuario comprobarUsuario(String usuario, String password);

    int cambiarContraseña(Usuario usuario, String nuevaPassword);

    int actualizarDatos(Usuario usuario, String userName, String mail);

    Usuario insertarAdmin(Usuario usuario);

    List<Usuario> getAllUsers();

    int deleteUser(Usuario usuario);

    List<Usuario> getAdmins();

    String validarUsuario(Usuario usuario);
}
