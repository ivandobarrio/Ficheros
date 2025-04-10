package controlador;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import modelo.Cliente;

public class GestionClienteBanco {
    private static final String NOMBRE_ARCHIVO = "clientes.txt";
    private static List<Cliente> clientes;

    public static void main(String[] args) {
        clientes = new ArrayList<>();
        cargarDatos();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú de Gestión de Clientes ---");
            System.out.println("1. Alta cliente");
            System.out.println("2. Baja cliente");
            System.out.println("3. Listar clientes");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    altaCliente(scanner);
                    break;
                case 2:
                    bajaCliente(scanner);
                    break;
                case 3:
                    listarClientes();
                    break;
                case 4:
                    guardarDatos();
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void altaCliente(Scanner scanner) {
        System.out.print("Introduce el DNI: ");
        String dni = scanner.nextLine();

        if (buscarCliente(dni) != null) {
            System.out.println("El cliente con DNI " + dni + " ya existe.");
            return;
        }

        System.out.print("Introduce el nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce la fecha de nacimiento (dd/MM/yyyy): ");
        String fechaNacimiento = scanner.nextLine();
        System.out.print("Introduce el saldo: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer

        Cliente nuevoCliente = new Cliente(dni, nombre, fechaNacimiento, saldo);
        clientes.add(nuevoCliente);
        Collections.sort(clientes, Comparator.comparing(Cliente::getDni));
        System.out.println("Cliente añadido.");
    }

    private static void bajaCliente(Scanner scanner) {
        System.out.print("Introduce el DNI del cliente a eliminar: ");
        String dni = scanner.nextLine();
        Cliente cliente = buscarCliente(dni);

        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente con DNI " + dni + " eliminado.");
        } else {
            System.out.println("No se encontró un cliente con DNI " + dni + ".");
        }
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("\n--- Listado de Clientes ---");
        double saldoMaximo = Double.NEGATIVE_INFINITY;
        double saldoMinimo = Double.POSITIVE_INFINITY;
        double totalSaldo = 0;

        for (Cliente cliente : clientes) {
            System.out.printf("DNI: %s, Nombre: %s, Saldo: %.2f, Edad: %d%n",
                    cliente.getDni(), cliente.getNombre(), cliente.getSaldo(), cliente.calcularEdad());
            totalSaldo += cliente.getSaldo();
            if (cliente.getSaldo() > saldoMaximo) {
                saldoMaximo = cliente.getSaldo();
            }
            if (cliente.getSaldo() < saldoMinimo) {
                saldoMinimo = cliente.getSaldo();
            }
        }

        double promedioSaldo = totalSaldo / clientes.size();
        System.out.printf("Saldo máximo: %.2f%n", saldoMaximo);
        System.out.printf("Saldo mínimo: %.2f%n", saldoMinimo);
        System.out.printf("Saldo promedio: %.2f%n", promedioSaldo);
    }

    private static void guardarDatos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (Cliente cliente : clientes) {
                writer.write(cliente.getDni() + "," + cliente.getNombre() + "," +
                        cliente.getFechaNacimiento() + "," + cliente.getSaldo());
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
                if (partes.length == 4) {
                    String dni = partes[0];
                    String nombre = partes[1];
                    String fechaNacimiento = partes[2];
                    double saldo = Double.parseDouble(partes[3]);
                    clientes.add(new Cliente(dni, nombre, fechaNacimiento, saldo));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo. Se iniciará una nueva lista de clientes.");
        } catch (IOException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    private static Cliente buscarCliente(String dni) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equalsIgnoreCase(dni)) {
                return cliente;
            }
        }
        return null;
    }
}