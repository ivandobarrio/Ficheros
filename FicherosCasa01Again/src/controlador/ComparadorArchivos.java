package controlador;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ComparadorArchivos {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java ComparadorArchivos <archivo1> <archivo2>");
            return;
        }

        String archivo1 = args[0];
        String archivo2 = args[1];

        try {
            compararArchivos(archivo1, archivo2);
        } catch (IOException e) {
            System.out.println("Error al leer los archivos: " + e.getMessage());
        }
    }

    private static void compararArchivos(String archivo1, String archivo2) throws IOException {
        try (BufferedReader br1 = new BufferedReader(new FileReader(archivo1));
             BufferedReader br2 = new BufferedReader(new FileReader(archivo2))) {

            String linea1;
            String linea2=null;
            int numeroLinea = 1;

            while ((linea1 = br1.readLine()) != null && (linea2 = br2.readLine()) != null) {
                if (!linea1.equals(linea2)) {
                    int longitudMinima = Math.min(linea1.length(), linea2.length());
                    int posicionDiferencia = -1;

                    for (int i = 0; i < longitudMinima; i++) {
                        if (linea1.charAt(i) != linea2.charAt(i)) {
                            posicionDiferencia = i;
                            break;
                        }
                    }

                    if (posicionDiferencia == -1) {
                        // Si no se encontró diferencia en los caracteres hasta la longitud mínima,
                        // la diferencia está en la longitud de las líneas.
                        posicionDiferencia = longitudMinima;
                    }

                    System.out.println("Los archivos son diferentes en la línea " + numeroLinea +
                            ", carácter " + (posicionDiferencia + 1) + ".");
                    return;
                }
                numeroLinea++;
            }

            // Si uno de los archivos tiene más líneas que el otro
            if (linea1 != null || linea2 != null) {
                System.out.println("Los archivos son diferentes en la línea " + numeroLinea + ".");
                return;
            }
        }

        System.out.println("Los archivos son iguales.");
    }
}