package modelo;

public class Cliente {
    private String dni;
    private String nombre;
    private String fechaNacimiento; // Formato: dd/MM/yyyy
    private double saldo;

    public Cliente(String dni, String nombre, String fechaNacimiento, double saldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.saldo = saldo;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public double getSaldo() {
        return saldo;
    }

    public int calcularEdad() {
        String[] partes = fechaNacimiento.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);

        // Calcular la edad
        java.util.Calendar fechaNacimientoCal = java.util.Calendar.getInstance();
        fechaNacimientoCal.set(anio, mes - 1, dia);
        java.util.Calendar fechaActual = java.util.Calendar.getInstance();

        int edad = fechaActual.get(java.util.Calendar.YEAR) - fechaNacimientoCal.get(java.util.Calendar.YEAR);
        if (fechaActual.get(java.util.Calendar.MONTH) < mes - 1 ||
            (fechaActual.get(java.util.Calendar.MONTH) == mes - 1 && fechaActual.get(java.util.Calendar.DAY_OF_MONTH) < dia)) {
            edad--;
        }
        return edad;
    }
}
