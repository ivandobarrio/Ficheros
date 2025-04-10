package controlador;
import java.io.*;

public class ParticionadorDeArchibos {
    public static void main(String[] args) {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Solicitar el nombre del archivo
            System.out.print("Introduce el nombre del archivo a particionar: ");
            String nombreArchivo = consoleReader.readLine();

            // Solicitar el tamaño de las partes
            System.out.print("Introduce el tamaño de las partes (ej. 10k, 5m, 100b): ");
            String tamañoParte = consoleReader.readLine();

            // Convertir el tamaño a bytes
            long tamañoEnBytes = convertirATamañoEnBytes(tamañoParte);
            if (tamañoEnBytes <= 0) {
                System.out.println("Tamaño no válido.");
                return;
            }

            // Particionar el archivo
            particionarArchivo(nombreArchivo, tamañoEnBytes);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static long convertirATamañoEnBytes(String tamañoParte) {
        char unidad = tamañoParte.charAt(tamañoParte.length() - 1);
        long tamaño = Long.parseLong(tamañoParte.substring(0, tamañoParte.length() - 1));

        switch (unidad) {
            case 'b':
                return tamaño; // bytes
            case 'k':
                return tamaño * 1024; // kilobytes
            case 'm':
                return tamaño * 1024 * 1024; // megabytes
            default:
                return -1; // tamaño no válido
        }
    }

    private static void particionarArchivo(String nombreArchivo, long tamañoEnBytes) {
        File archivoOriginal = new File(nombreArchivo);

        // Verificar si el archivo existe
        if (!archivoOriginal.exists()) {
            System.out.println("El archivo no existe: " + nombreArchivo);
            return;
        }

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(archivoOriginal)) ) {
            byte[] buffer = new byte[(int) tamañoEnBytes];
            int bytesLeidos;
            int parte = 1;

            while ((bytesLeidos = inputStream.read(buffer)) != -1) {
                String nuevoNombreArchivo = String.format("parte%03d_%s", parte, archivoOriginal.getName());
                try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(nuevoNombreArchivo))) {
                    outputStream.write(buffer, 0, bytesLeidos);
                }
                System.out.println("Creado: " + nuevoNombreArchivo);
                parte++;
            }

        } catch (IOException e) {
            System.out.println("Error al particionar el archivo: " + e.getMessage());
        }
    }
}