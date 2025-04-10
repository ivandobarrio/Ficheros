package modelo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Encriptador {
    
    private Map<Character, Character> mapaCodificacion;

    public Encriptador(String archivoCodec) throws IOException {
        mapaCodificacion = new HashMap<>();
        cargarCodificacion(archivoCodec);
    }

    private void cargarCodificacion(String archivoCodec) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCodec))) {
            String linea = br.readLine();
            if (linea != null) {
                String[] alfabeto = linea.split(" ");
                linea = br.readLine();
                if (linea != null) {
                    String[] cifrado = linea.split(" ");
                    for (int i = 0; i < alfabeto.length; i++) {
                        mapaCodificacion.put(alfabeto[i].charAt(0), cifrado[i].charAt(0));
                    }
                }
            }
        }
    }

    public String codificar(String texto) {
        StringBuilder textoCifrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (mapaCodificacion.containsKey(c)) {
                textoCifrado.append(mapaCodificacion.get(c));
            } else {
                textoCifrado.append(c); // Si no está en el mapa, se deja el carácter original
            }
        }
        return textoCifrado.toString();
    }

    public void encriptarArchivo(String archivoEntrada, String archivoSalida) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String textoCifrado = codificar(linea);
                bw.write(textoCifrado);
                bw.newLine();
            }
        }
    }
}