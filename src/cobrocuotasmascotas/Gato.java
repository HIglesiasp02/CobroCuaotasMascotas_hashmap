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
public class Gato extends Mascota {

    private int clase;

    public Gato() {
    }

    public Gato(Propietario propietario, String identificador, int clase) {
        super(propietario, identificador);
        this.clase = clase;
    }

    public int getClase() {
        return clase;
    }

    public void setClase(int clase) {
        this.clase = clase;
    }

    public static boolean validarClase(String clase) {
        boolean valido = false;
        int num = Integer.parseInt(clase);
        if (num >= 1 && num <= 4) {
            valido = true;
        }
        return valido;
    }

    @Override
    public int calcularCuota() {
        int cuota = super.calcularCuota();
        for (int i = 1; i < this.clase; i = i + 1) {
            cuota = cuota + 15;
        }
        return cuota;
    }

    @Override
    public String mostrar() {
        return "Mascota{" + "propietario=" + propietario.mostrarPropietario() + ", identificador=" + identificador + ", clase=" + clase + ", cuota=" + calcularCuota() + '}';
    }
}
