import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Receta implements Serializable {
    //Desarrollar una aplicación denominada GestorRecetas que permita gestionar un catálogo de recetas utilizando distintos formatos de almacenamiento según la finalidad de la información.
    //
    //Requisitos
    //
    //1. Gestión del catálogo
    //
    //Crear una clase Receta con los atributos necesarios para representar una receta.
    //
    //La información del catálogo deberá almacenarse en un fichero binario denominado recetas.dat.
    //
    //La aplicación deberá permitir:
    //
    //    Crear un catálogo inicial con varias recetas.
    //    Mostrar todas las recetas almacenadas.
    //    Buscar una receta mediante su identificador.
    //    Modificar el tiempo de preparación de una receta.
    //    Añadir nuevas recetas al catálogo.
    //
    //2. Gestión del fichero binario
    //
    //Antes de acceder al catálogo, comprobar si el fichero recetas.dat existe.
    //
    //Si no existe, la aplicación deberá crear automáticamente el catálogo inicial para permitir su funcionamiento.
    //
    //3. Exportación del catálogo
    //
    //Incorporar una opción que permita generar un documento denominado recetas.xml con todas las recetas almacenadas.
    //
    //El documento deberá contener una estructura XML correctamente organizada.
    //
    //4. Configuración de la aplicación
    //
    //Crear una clase Configuracion para almacenar la información de la aplicación.
    //
    //Al finalizar la ejecución, guardar en configuracion.json la última opción del menú utilizada y el número total de recetas almacenadas.
    //
    //Al iniciar nuevamente la aplicación, recuperar dicha información desde el documento JSON.
    //
    //5. Organización del código
    //
    //Organizar la aplicación utilizando clases y métodos independientes para cada funcionalidad.
    //
    //Gestionar adecuadamente las excepciones que puedan producirse durante el acceso a los distintos documentos.
    private String receta;
    private int id;
    private static int cont = 0;
    private ArrayList<String> ingredientes = new ArrayList<>();
    private String tiempoPreparacion;
    public Receta(){
        cont++;
        id = cont;
    }
    public Receta(String receta,ArrayList<String>ingredientes,String tiempoPreparacion){
        cont++;
        id = cont;
        this.receta = receta;
        this.ingredientes = ingredientes;
        this.tiempoPreparacion = tiempoPreparacion;
    }
    public  void crearCatalogoInicial() {

        if (this.id == 1){
            this.receta = "Pulpo a la gallega";
            this.tiempoPreparacion = "Dos horas y media";
            ingredientes.add("Pulpo");
            ingredientes.add("Patata");
        }
        else if (this.id==2){
            this.receta = "Perritos calientes";
            this.tiempoPreparacion = "35 minutos";
            ingredientes.add("Salchicha");
            ingredientes.add("Pan");

        }
        else if (this.id == 3){
            this.receta = "Caldo de pollo";
            this.tiempoPreparacion = "1 hora";
            ingredientes.add("Pollo");
            ingredientes.add("Verduras");
            ingredientes.add("Caldo");
        }
        else{
            System.out.println("Catálogo inicial ya creado");
        }
    }

    public String getReceta() {
        return receta;
    }

    public int getId() {
        return id;
    }


    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public String getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(String tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }
}
