import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CrearJson {
    private static final File FICHERO = new File("src/configuracion.json");

    public static void crearJson(Configuracion configuracion) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fichero = new FileWriter(FICHERO)) {
            gson.toJson(configuracion, fichero);
            System.out.println("Documento JSON creado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el documento JSON.");
        }
    }

    public static Configuracion leerJson() {
        if (!FICHERO.exists()) {
            return null;
        }
        Gson gson = new Gson();
        try (FileReader fichero = new FileReader(FICHERO)) {
            return gson.fromJson(fichero, Configuracion.class);
        } catch (IOException e) {
            System.out.println("Error al leer el documento JSON.");
            return null;
        }
    }
}
