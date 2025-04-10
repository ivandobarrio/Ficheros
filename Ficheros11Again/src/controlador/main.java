package controlador;

import java.io.IOException;

import modelo.Encriptador;

public class main {

	public static void main(String[] args) {
		 if (args.length != 3) {
	            System.out.println("Uso: java Encriptador <archivo_codec> <archivo_entrada> <archivo_salida>");
	            return;
	        }

	        String archivoCodec = args[0];
	        String archivoEntrada = args[1];
	        String archivoSalida = args[2];

	        try {
	            Encriptador encriptador = new Encriptador(archivoCodec);
	            encriptador.encriptarArchivo(archivoEntrada, archivoSalida);
	            System.out.println("Archivo cifrado creado: " + archivoSalida);
	        } catch (IOException e) {
	            System.out.println("Ocurrió un error: " + e.getMessage());
	        }
	    }
	}