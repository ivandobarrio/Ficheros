package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		 int contadorCaracteres = 0;
	     int contadorLineas = 0;
	     int contadorPalabras = 0;
	     
	     try(BufferedReader br = new BufferedReader(new FileReader("src/modelo/llamadocarla.txt"))){
	    	 String linea;
	    	 while((linea=br.readLine())!=null) {
	    		 contadorCaracteres += linea.length();
	    		 contadorLineas++;
	    		 String[] palabras = linea.trim().split(" ");
	    		 contadorPalabras += palabras.length;
	    	 }
	     } catch (IOException e) {
			System.out.println("Error al leer el archivo");
		}
	     System.out.println("Número de caracteres: " + contadorCaracteres);
	     System.out.println("Número de líneas: " + contadorLineas);
	     System.out.println("Número de palabras: " + contadorPalabras);
	}

}
