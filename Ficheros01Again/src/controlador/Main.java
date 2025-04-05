package controlador;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer entero = leerEntero();
		System.out.println("El numero entero ha sido leido correctamente " + entero);
	}
	public static Integer leerEntero() {
		Scanner sc = new Scanner(System.in);
		
		Integer numero = null;
		boolean esValido = false;
		
		while(!esValido) {
			System.out.println("Introduce un numero entero: ");
			String entrada = sc.nextLine();
			
			try {
				numero = Integer.parseInt(entrada);
				esValido = true;
			}catch(NumberFormatException e) {
				System.out.println("Error, la entrada no es validad");
			}
		}
		return numero;
	}
}
