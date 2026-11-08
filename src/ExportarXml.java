import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class ExportarXml {
    public static void exportarXlm(File ficheroDat)  {
        ArrayList<Receta> listaRecetas = new ArrayList<>();
        try(ObjectInputStream recuperarObjetos= new ObjectInputStream(new FileInputStream(ficheroDat))) {
            while (true){
                Receta receta = (Receta)recuperarObjetos.readObject();
                listaRecetas.add(receta);
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Fichero leido");
        }
        try {
// Crea el objeto de configuración del analizador DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
// Crea el analizador DOM
            DocumentBuilder builder = factory.newDocumentBuilder();
// Crea un documento XML vacío
            Document documento = builder.newDocument();
            Element recetas = documento.createElement("recetas");
            documento.appendChild(recetas);
            for (Receta receta:listaRecetas){
                Element id = documento.createElement("id");
                id.setTextContent(Integer.toString(receta.getId()));
                recetas.appendChild(id);
                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(receta.getReceta());
                id.appendChild(nombre);
                for (int i = 0; i < receta.getIngredientes().size();i++){
                    Element ingrediente = documento.createElement("ingrediente");
                    ingrediente.setTextContent(receta.getIngredientes().get(i));
                    id.appendChild(ingrediente);
                }
                Element tiempo = documento.createElement("tiempo");
                tiempo.setTextContent(receta.getTiempoPreparacion());
                id.appendChild(tiempo);
            }

// Crea el objeto Transformer
            Transformer transformer =
                    TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
// Guarda el documento XML
            transformer.transform(new DOMSource(documento),new
                    StreamResult(new File("src/recetas.xml")));
            System.out.println("Documento XML creado correctamente.");
    } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
