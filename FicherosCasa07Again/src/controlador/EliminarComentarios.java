package controlador;
import java.io.*;

public class EliminarComentarios {
    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            // Solicitar el nombre del archivo
            System.out.print("Introduce el nombre del archivo Java: ");
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String nombreArchivo = consoleReader.readLine();

            // Crear el nombre del nuevo archivo
            String nuevoNombreArchivo = "sin_comentarios_" + nombreArchivo;

            // Inicializar los flujos de entrada y salida
            reader = new BufferedReader(new FileReader(nombreArchivo));
            writer = new BufferedWriter(new FileWriter(nuevoNombreArchivo));

            String linea;
            boolean enComentarioMultilinea = false;

            // Leer el archivo línea por línea
            while ((linea = reader.readLine()) != null) {
                // Eliminar comentarios de múltiples líneas
                if (enComentarioMultilinea) {
                    if (linea.contains("*/")) {
                        enComentarioMultilinea = false;
                        linea = linea.substring(linea.indexOf("*/") + 2);
                    } else {
                        continue; // Ignorar toda la línea si está dentro de un comentario
                    }
                }

                // Eliminar comentarios de una sola línea
                int indexComentario = linea.indexOf("//");
                if (indexComentario != -1) {
                    linea = linea.substring(0, indexComentario);
                }

                // Eliminar comentarios de múltiples líneas que comienzan en la línea actual
                indexComentario = linea.indexOf("/*");
                if (indexComentario != -1) {
                    enComentarioMultilinea = true;
                    linea = linea.substring(0, indexComentario);
                }

                // Escribir la línea sin comentarios en el nuevo archivo
                linea = linea.trim(); // Eliminar espacios en blanco al principio y al final
                if (!linea.isEmpty()) {
                    writer.write(linea);
                    writer.newLine();
                }
            }

            System.out.println("Se ha creado el archivo: " + nuevoNombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        } finally {
            // Cerrar los flujos
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar los flujos: " + e.getMessage());
            }
        }
    }
}