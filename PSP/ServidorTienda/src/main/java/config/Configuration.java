package config;

import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter @Setter
public class Configuration {
    private static Configuration config;

    private Configuration() {

    }

    public static Configuration cargarInstance(InputStream file) {

        if (config == null) {
            try {
                Yaml yaml = new Yaml();
                config = yaml.loadAs(file,
                        Configuration.class);
            } catch (Exception ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return config;
    }

    public static Configuration getInstance() {

        return config;
    }

    private String ruta;
    private String user;
    private String password;
}
