/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobrocuotasmascotas;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author daw120
 */
public class CobroCuotasMascotas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Mascota> mascotas = new HashMap<String, Mascota>();

        String opt;

        do {

            System.out.println("1 - Añadir nueva mascota");
            System.out.println("2 - Visualizar listado");
            System.out.println("3 - Eliminar mascota");
            System.out.println("4 - Calcular ingresos");
            System.out.println("0 - Salir\n");
            System.out.print("Escoge una opción: ");
            opt = sc.nextLine();

            switch (opt) {
                case "1":
                    String identificador;
                    Propietario propietario;
                    do {
                        System.out.println("Introduce el identificador de la nueva mascota (consta de 5 numeros, un (-) y entre 2 y 6 letras mayusculas, ejemplo: 99999-ASD): ");
                        identificador = sc.nextLine();
                        if (!Mascota.validarIdentificador(identificador)) {
                            System.out.println("Formato no valido");
                        }
                    } while (!Mascota.validarIdentificador(identificador));

                    propietario = crearPropietario();

                    System.out.print("¿Un Perro(1) o un Gato(2)? ");
                    opt = sc.nextLine();
                    if (!opt.equals("1") && !opt.equals("2")) {
                        System.out.println("Opcion no valida");
                    }

                    int dato = anadirMascota(propietario, identificador, opt);
                    if (opt.equals("1")) {
                        mascotas.put(identificador, new Perro(propietario, identificador, dato));
                    }
                    if (opt.equals("2")) {
                        mascotas.put(identificador, new Gato(propietario, identificador, dato));
                    }
                    escribirFichero(mascotas);

                    break;
                case "2":
                    visualizarListado(mascotas);

                    break;
                case "3":
                    eliminarMascota(mascotas);
                    escribirFichero(mascotas);

                    break;
                case "4":
                    calcularIngresos(mascotas);

                    break;
                case "0":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
            System.out.println("\n");
        } while (!opt.equals("0"));

    }

    public static Propietario crearPropietario() {
        Scanner sc = new Scanner(System.in);
        String nombre;
        String apellido;
        String dni;
        Propietario propietario;

        System.out.print("Introduce el nombre del propietario: ");
        nombre = sc.nextLine();
        System.out.print("Introduce el apellido del propietario: ");
        apellido = sc.nextLine();
        do {
            System.out.print("Introduce el dni del propietario (Ejemplo:12345678Z): ");
            dni = sc.nextLine();
            if (!Propietario.validarDni(dni)) {
                System.out.println("Formato no valido");
            }
        } while (!Propietario.validarDni(dni));
        propietario = new Propietario(nombre, apellido, dni);
        return propietario;
    }

    public static int anadirMascota(Propietario propietario, String identidad, String opt) {
        Scanner sc = new Scanner(System.in);
        int dato = 0;
        do {

            if (opt.equals("1")) {
                String clasep;
                do {
                    System.out.print("Clase del perro(1-4):");
                    clasep = sc.nextLine();
                    if (!Perro.validarClase(clasep)) {
                        System.out.println("Clase incorrecto");
                    }
                } while (!Perro.validarClase(clasep));
                dato = Integer.parseInt(clasep);
            }

            if (opt.equals("2")) {
                String claseg;

                do {
                    System.out.print("Clase del gato(1-4): ");
                    claseg = sc.nextLine();
                    if (!Gato.validarClase(claseg)) {
                        System.out.println("Valor introducido incorrecto");
                    }
                } while (!Gato.validarClase(claseg));
                dato = Integer.parseInt(claseg);
            }

            if (!opt.equals("1") && !opt.equals("2")) {
                System.out.print("¿Un Perro(1) o un Gato(2)? ");
                opt = sc.nextLine();
                if (!opt.equals("1") && !opt.equals("2")) {
                    System.out.println("Opcion no valida");
                }
            }
        } while (!opt.equals("1") && !opt.equals("2"));
        return dato;
    }

    public static void visualizarListado(HashMap<String, Mascota> mascotas) {
        for (String i : mascotas.keySet()) {
            System.out.println("Identificador: " + i + " Contenido: " + mascotas.get(i).mostrar());
        }
    }

    public static void eliminarMascota(HashMap<String, Mascota> mascotas) {
        Scanner sc = new Scanner(System.in);
        String identificador;
        do {
            System.out.print("Mascota ha eliminar(identificador mascota):");
            identificador = sc.nextLine();
            if (!mascotas.containsKey(identificador)) {
                System.out.println("Valor introducido incorrecto.");
            }
        } while (!mascotas.containsKey(identificador));
        mascotas.remove(identificador);
    }

    public static void calcularIngresos(HashMap<String, Mascota> mascotas) {
        int perros = 0;
        int gatos = 0;
        for (String i : mascotas.keySet()) {
            if (mascotas.get(i) instanceof Perro) {
                perros = perros + mascotas.get(i).calcularCuota();
            }
            if (mascotas.get(i) instanceof Gato) {
                gatos = gatos + mascotas.get(i).calcularCuota();
            }
        }
        System.out.println("Tasas totales de perros: " + perros);
        System.out.println("Tasas totales de gatos: " + gatos);
        System.out.println("Tasas totales: " + (perros + gatos));

    }

    public static void escribirFichero(HashMap<String, Mascota> mascotas) {
        FileWriter fichero;
        try {
            fichero = new FileWriter("mascotas.txt");
            for (String i : mascotas.keySet()) {
                fichero.write(mascotas.get(i).mostrar() + "\n");
            }
            fichero.close();
        } catch (Exception e) {
            System.out.println("Mensaje de la excepción: " + e.getMessage());
        }
    }

}
