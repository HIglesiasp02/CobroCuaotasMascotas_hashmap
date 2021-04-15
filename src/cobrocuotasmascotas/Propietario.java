/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobrocuotasmascotas;

/**
 *
 * @author daw120
 */
public class Propietario {

    private String nombre;
    private String apellidos;
    private String dni;

    public Propietario() {
    }

    public Propietario(String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public static boolean validarDni(String dni) {
        boolean valido = false;
        if (dni.matches("[0-9]{8}[A-Z]{1}")) {
            valido = true;
        }
        if (valido) {
            String[] letra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", 
                "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
            int numdni = Integer.parseInt(dni.substring(0, 8));
            int numdni2 = numdni % 23;
            String comprobar = numdni + letra[numdni2];
            if (!dni.equalsIgnoreCase(comprobar)) {
                valido = false;
            }
        }
        return valido;
    }

    @Override
    public String toString() {
        return "Propietario{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + '}';
    }
    
    public String mostrarPropietario() {
        return this.toString(); 
    }
    
}


