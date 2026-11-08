import java.io.*;
import java.util.ArrayList;

// //Desarrollar una aplicación denominada GestorRecetas que permita gestionar un catálogo de recetas utilizando distintos formatos de almacenamiento según la finalidad de la información.
//    //
//    //Requisitos
//    //
//    //1. Gestión del catálogo
//    //
//    //Crear una clase Receta con los atributos necesarios para representar una receta.
//    //
//    //La información del catálogo deberá almacenarse en un fichero binario denominado recetas.dat.
//    //
//    //La aplicación deberá permitir:
//    //
//    //    Crear un catálogo inicial con varias recetas.
//    //    Mostrar todas las recetas almacenadas.
//    //    Buscar una receta mediante su identificador.
//    //    Modificar el tiempo de preparación de una receta.
//    //    Añadir nuevas recetas al catálogo.
//    //
//    //2. Gestión del fichero binario
//    //
//    //Antes de acceder al catálogo, comprobar si el fichero recetas.dat existe.
//    //
//    //Si no existe, la aplicación deberá crear automáticamente el catálogo inicial para permitir su funcionamiento.
//    //
//    //3. Exportación del catálogo
//    //
//    //Incorporar una opción que permita generar un documento denominado recetas.xml con todas las recetas almacenadas.
//    //
//    //El documento deberá contener una estructura XML correctamente organizada.
//    //
//    //4. Configuración de la aplicación
//    //
//    //Crear una clase Configuracion para almacenar la información de la aplicación.
//    //
//    //Al finalizar la ejecución, guardar en configuracion.json la última opción del menú utilizada y el número total de recetas almacenadas.
//    //
//    //Al iniciar nuevamente la aplicación, recuperar dicha información desde el documento JSON.
//    //
//    //5. Organización del código
//    //
//    //Organizar la aplicación utilizando clases y métodos independientes para cada funcionalidad.
//    //
//    //Gestionar adecuadamente las excepciones que puedan producirse durante el acceso a los distintos documentos.
public class GestionarRecetas {
    public static void guardarRecetas(Receta[] recetas, File ficheroDat) {
        try (ObjectOutputStream guardarReceta = new ObjectOutputStream(new FileOutputStream(ficheroDat))) {
            for (Receta receta : recetas) {
                guardarReceta.writeObject(receta);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static int contarRecetas(File ficheroDat) {
        int contador = 0;
        try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(ficheroDat))) {
            while (true) {
                lector.readObject();
                contador++;
            }
        } catch (IOException e) {
            // Fin del fichero: se han contado todas las recetas.
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return contador;
    }

    public static void mostrarRecetas(File ficheroDat)  {
        try (ObjectInputStream mostrarReceta = new ObjectInputStream(new FileInputStream(ficheroDat))) {
            while (true) {
                Receta receta = (Receta) mostrarReceta.readObject();
                System.out.println("Id receta: " + receta.getId());
                System.out.println("Nombre receta: " + receta.getReceta());
                System.out.println("Ingredientes");
                for(String ingrediente: receta.getIngredientes()){
                    System.out.println("    "+ingrediente);
                }
                System.out.println("Tiempo de preparación: " + receta.getTiempoPreparacion());
            }
        }catch(IOException e){
                System.out.println("Fichero leido");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean buscarReceta(File ficheroDat, int id){
        try (ObjectInputStream mostrarReceta = new ObjectInputStream(new FileInputStream(ficheroDat))) {
            while (true) {
                Receta receta = (Receta)mostrarReceta.readObject();
                if (receta.getId()==id){
                    System.out.println("Id receta: " + receta.getId());
                    System.out.println("Nombre receta: " + receta.getReceta());
                    System.out.println("Ingredientes");
                    for(String ingrediente: receta.getIngredientes()){
                        System.out.println("    "+ingrediente);
                    }
                    System.out.println("Tiempo de preparación: " + receta.getTiempoPreparacion());
                    return true;
                }
            }
        }
        catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }
    public static void modificarTiempoReceta(File ficheroDat,int id,String nuevoTiempoPreparacion){
        ArrayList<Receta> recetas = new ArrayList<>();
        try (ObjectInputStream mostrarReceta = new ObjectInputStream(new FileInputStream(ficheroDat))) {
            while (true) {
                Receta receta = (Receta) mostrarReceta.readObject();
                if (receta.getId()==id){
                    receta.setTiempoPreparacion(nuevoTiempoPreparacion);
                }
                recetas.add(receta);
            }
        }catch(IOException e){
            System.out.println("Fichero leido");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (ObjectOutputStream guardarReceta = new ObjectOutputStream(new FileOutputStream(ficheroDat))) {
            for (Receta receta : recetas) {
                guardarReceta.writeObject(receta);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static  void anhadirReceta(File ficheroDat,Receta nuevaReceta) {


        ArrayList<Receta> recetas = new ArrayList<>();
        try (ObjectInputStream mostrarReceta = new ObjectInputStream(new FileInputStream(ficheroDat))) {
            while (true) {
                Receta receta = (Receta) mostrarReceta.readObject();

                recetas.add(receta);
            }
        } catch (IOException e) {
            System.out.println("Fichero leido");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        recetas.add(nuevaReceta);
        try (ObjectOutputStream guardarReceta = new ObjectOutputStream(new FileOutputStream(ficheroDat))) {
            for (Receta receta : recetas) {
                guardarReceta.writeObject(receta);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
