import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
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
    public static void main(String[] args) {
        int totalRecetas = 0;
        Scanner sc  = new Scanner(System.in);
        String ruta = "src/recetas.dat";
        File ficheroDat = new File(ruta);
        Receta recetaInicial1 = new Receta();
        Receta recetaInicial2 = new Receta();
        Receta recetaInicial3 = new Receta();
        Receta[] listaRecetasIniciales = {recetaInicial1,recetaInicial2,recetaInicial3};
        recetaInicial1.crearCatalogoInicial();
        recetaInicial2.crearCatalogoInicial();
        recetaInicial3.crearCatalogoInicial();
        Configuracion configuracion = CrearJson.leerJson();
        if (configuracion != null) {
            System.out.println("Última opción utilizada: " + configuracion.getUltimaOpcion());
            System.out.println("Total de recetas almacenadas: " + configuracion.getTotalRecetas());
        }
        int select;
        int ultimaOpcion = 0;
        boolean continuar = true;
        while(continuar){
            System.out.println("1.Crear un catálogo inicial con varias recetas(En caso de tener las recetas creadas borrara todo y volvera a las recetas por defecto).\n" +
                            "2.Mostrar todas las recetas almacenadas.\n" +
                            "3.Buscar una receta mediante su identificador.\n" +
                            "4.Modificar el tiempo de preparación de una receta.\n" +
                            "5.Añadir nuevas recetas al catálogo.\n"+
                            "6.Generar XML,\n"+
                            "7.Salir");
            select = sc.nextInt();
            ultimaOpcion = select;
            if (select==1){
                if (!ficheroDat.exists()){
                    GestionarRecetas.guardarRecetas(listaRecetasIniciales,ficheroDat);

                }
            } else if (select==2) {
                GestionarRecetas.mostrarRecetas(ficheroDat);
            }
            else if (select==3){
                System.out.println("Introduzca el ID de la receta que desea buscar.");
                if(GestionarRecetas.buscarReceta(ficheroDat,sc.nextInt())){
                    System.out.println("Receta encontrada");
                }else{
                    System.out.println("Receta no encontrada");
                }
            } else if (select==4) {
                System.out.println("Introduzca el id de la receta cuyo tiempo de preparación desea cambiar");
                int idReceta = sc.nextInt();
                sc.nextLine();
                System.out.println("Introduzca el nuevo tiempo de preparación");

                String tiempo = sc.nextLine();
                GestionarRecetas.modificarTiempoReceta(ficheroDat,idReceta,tiempo);
            }
            else if (select==5){
                System.out.println("Introduzca el nombre de la receta");
                String nombre = sc.next();
                System.out.println("Introduzca la cantidad de ingredientes que va a tener");
                int cant = sc.nextInt();
                ArrayList<String> listaIngredientes = new ArrayList<>();
                for (int i = 0; i < cant; i++){
                    System.out.println("Introduzca el ingrediente " + (i+1));
                    sc.nextLine();
                    String ingrediente = sc.nextLine();
                    listaIngredientes.add(ingrediente);
                }

                System.out.println("Introduzca el tiempo de preparacion(puede ser texto)");
                String tiempo = sc.nextLine();
                GestionarRecetas.anhadirReceta(ficheroDat,new Receta(nombre,listaIngredientes,tiempo));
            }
            else if(select==6){
                ExportarXml.exportarXlm(ficheroDat);
            }
            else{
                continuar=false;
            }
            totalRecetas = GestionarRecetas.contarRecetas(ficheroDat);
            CrearJson.crearJson(new Configuracion(ultimaOpcion, totalRecetas));//Lo he metido dentro a pesar de
            // que sea indeficiente porque si no siempre se guardara la opcion 7
        }


    }
}
