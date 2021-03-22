package servicios;

import dao.modelo.Lector;
import dao.modelo.Usuario;
import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.time.LocalDate;

public interface ServiciosLectores {
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

    Lector getLector(Usuario u);

    Lector addLector(Lector lector);

    int actualizarDatosLector(Usuario user, String nombre, LocalDate date);

    String validarLector(Lector object);
}
