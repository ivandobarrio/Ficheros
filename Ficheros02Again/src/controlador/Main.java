package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		//Declaramos una variable que se encargara de hacer de el contador de la suma
		double suma = 0;
		//Declaramos una variable contador que se encargará de contabilizar el proceso
		int contador = 0;
		
		try(BufferedReader br = new BufferedReader(new FileReader("src/controlador/NumerosReales.txt"))){
			String linea;
			while((linea = br.readLine())!= null) {
				try {
					double numero = Double.parseDouble(linea);
					suma += numero;
					contador++;
				}catch(NumberFormatException e) {
					System.out.println("Error en la linea: "+ linea + "no es un numero valido");
				}
			}
		}catch(IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}
		if (contador > 0) {
			double media = suma/contador;
			System.out.println("Suma: " + suma);
			System.out.println("Media: " + media);
		}else {
			System.out.println("No se encontraron numeros validos");
		}
	}

}
