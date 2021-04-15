/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobrocuotasmascotas;

import java.util.Objects;

/**
 *
 * @author daw120
 */
public abstract class Mascota implements Tarifable {

    public Propietario propietario;
    public String identificador;

    public Mascota() {
    }

    public Mascota(Propietario propietario, String identificador) {
        this.propietario = propietario;
        this.identificador = identificador;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public static boolean validarIdentificador(String identificador) {
        boolean valido = false;
        if (identificador.matches("[0-9]{5}(-)[A-ZÑ]{2,6}")) {
            valido = true;
        }
        return valido;
    }

    @Override
    public int calcularCuota() {
        return 350;
    }

    public String mostrar() {
        return "Mascota{" + "propietario=" + propietario.mostrarPropietario() + ", identificador=" + identificador + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mascota other = (Mascota) obj;
        if (!Objects.equals(this.identificador, other.identificador)) {
            return false;
        }
        return true;
    }

    
}
