package controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

	public class RegistroTemperaturas {
	    private static final String NOMBRE_ARCHIVO = "temperaturas.txt";
	    private static List<Registro> registros;

	    public static void main(String[] args) {
	        registros = new ArrayList<>();
	        cargarDatos();

	        Scanner scanner = new Scanner(System.in);
	        int opcion;

	        do {
	            System.out.println("\n--- Menú de Registro de Temperaturas ---");
	            System.out.println("1. Registrar nueva temperatura");
	            System.out.println("2. Mostrar historial de registros");
	            System.out.println("3. Salir");
	            System.out.print("Elige una opción: ");
	            opcion = scanner.nextInt();
	            scanner.nextLine(); // Limpiar el buffer

	            switch (opcion) {
	                case 1:
	                    registrarTemperatura(scanner);
	                    break;
	                case 2:
	                    mostrarHistorial();
	                    break;
	                case 3:
	                    guardarDatos();
	                    System.out.println("Saliendo de la aplicación...");
	                    break;
	                default:
	                    System.out.println("Opción no válida. Intenta de nuevo.");
	            }
	        } while (opcion != 3);

	        scanner.close();
	    }

	    private static void registrarTemperatura(Scanner scanner) {
	        System.out.print("Introduce la temperatura máxima: ");
	        double temperaturaMaxima = scanner.nextDouble();
	        System.out.print("Introduce la temperatura mínima: ");
	        double temperaturaMinima = scanner.nextDouble();

	        registros.add(new Registro(temperaturaMaxima, temperaturaMinima));
	        System.out.println("Temperaturas registradas.");
	    }

	    private static void mostrarHistorial() {
	        if (registros.isEmpty()) {
	            System.out.println("No hay registros de temperaturas.");
	            return;
	        }

	        System.out.println("\n--- Historial de Registros ---");
	        double maxTemp = Double.NEGATIVE_INFINITY;
	        double minTemp = Double.POSITIVE_INFINITY;

	        for (Registro registro : registros) {
	            System.out.printf("Máxima: %.2f, Mínima: %.2f%n", registro.getTemperaturaMaxima(), registro.getTemperaturaMinima());
	            if (registro.getTemperaturaMaxima() > maxTemp) {
	                maxTemp = registro.getTemperaturaMaxima();
	            }
	            if (registro.getTemperaturaMinima() < minTemp) {
	                minTemp = registro.getTemperaturaMinima();
	            }
	        }

	        System.out.printf("Temperatura máxima más alta: %.2f%n", maxTemp);
	        System.out.printf("Temperatura mínima más baja: %.2f%n", minTemp);
	    }

	    private static void guardarDatos() {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
	            for (Registro registro : registros) {
	                writer.write(registro.getTemperaturaMaxima() + "," + registro.getTemperaturaMinima());
	                writer.newLine();
	            }
	            System.out.println("Datos guardados en " + NOMBRE_ARCHIVO);
	        } catch (IOException e) {
	            System.out.println("Error al guardar los datos: " + e.getMessage());
	        }
	    }

	    private static void cargarDatos() {
	        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
	            String linea;
	            while ((linea = reader.readLine()) != null) {
	                String[] partes = linea.split(",");
	                if (partes.length == 2) {
	                    double temperaturaMaxima = Double.parseDouble(partes[0]);
	                    double temperaturaMinima = Double.parseDouble(partes[1]);
	                    registros.add(new Registro(temperaturaMaxima, temperaturaMinima));
	                }
	            }
	        } catch (FileNotFoundException e) {
	            System.out.println("No se encontró el archivo. Se iniciará un nuevo registro.");
	        } catch (IOException e) {
	            System.out.println("Error al cargar los datos: " + e.getMessage());
	        }
	    }

	    static class Registro {
	        private double temperaturaMaxima;
	        private double temperaturaMinima;

	        public Registro(double temperaturaMaxima, double temperaturaMinima) {
	            this.temperaturaMaxima = temperaturaMaxima;
	            this.temperaturaMinima = temperaturaMinima;
	        }

	        public double getTemperaturaMaxima() {
	            return temperaturaMaxima;
	        }

	        public double getTemperaturaMinima() {
	            return temperaturaMinima;
	        }
	    }
	}