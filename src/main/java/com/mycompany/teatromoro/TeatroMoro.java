package com.mycompany.teatromoro;

import java.util.Scanner;

public class TeatroMoro {
    // Arrays para almacenar información
    static String[] asientos = new String[40];
    static int entradasDisponiblesVIP = 5;
    static int entradasDisponiblesPlateaAlta = 5;
    static int entradasDisponiblesPlateaBaja = 5;
    static int entradasDisponiblesPalcos = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opcion;

        inicializarAsientos();

        do {
            System.out.println("Bienvenido al Teatro Moro!");
            System.out.println("Venga a disfrutar de nuestra funcion");
            System.out.println("Que desea hacer?");
            System.out.println("1. Comprar Entrada");
            System.out.println("2. Entradas disponibles");
            System.out.println("3. Salir");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    comprarEntrada(scanner);
                    break;
                case "2":
                    verDisponibilidad();
                    break;
                case "3":
                    System.out.println("Gracias por visitar el Teatro Moro");
                    break;
                default:
                    System.out.println("Por favor escoegr una opcion del menu");
            }
        } while (!opcion.equals("3"));
    }

    public static void comprarEntrada(Scanner scanner) {
        String categoria, entrada, continuar = null;
        int precioBase = 0;

        do {
            System.out.println("Por favor, ingrese su edad:");
            int edad;
            try {
                edad = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una edad valida");
                continue; //Volver al loop
            }

            if (edad < 18) {
                entrada = "estudiante";
            } else if (edad >= 60) {
                entrada = "tercera edad";
            } else {
                entrada = "general";
            }

            System.out.println("Usted califica como " + entrada + " para la entrada");

            System.out.println("Que categoria necesitas?");
            System.out.println("Por favor escribir la opción en minúscula");
            System.out.println("Zona A - Vip (vip)");
            System.out.println("Zona B - Platea baja (platea baja)");
            System.out.println("Zona C - Platea alta (platea alta)");
            System.out.println("Zona D - Palcos (palcos)");

            categoria = scanner.nextLine();

            switch (categoria.toLowerCase()) {
                case "vip":
                    if (entradasDisponiblesVIP > 0) {
                        precioBase = entrada.equalsIgnoreCase("estudiante") ? 13000 : 25000;
                        asientos[getPrimerAsientoDisponible("VIP")] = "Ocupado";
                        entradasDisponiblesVIP--;
                    } else {
                        System.out.println("Lo siento, no hay entradas disponibles en la zona VIP");
                        return;
                    }
                    break;
                case "platea baja":
                    if (entradasDisponiblesPlateaBaja > 0) {
                        precioBase = entrada.equalsIgnoreCase("estudiante") ? 10000 : 19000;
                        asientos[getPrimerAsientoDisponible("Platea Baja")] = "Ocupado";
                        entradasDisponiblesPlateaBaja--;
                    } else {
                        System.out.println("Lo siento, no hay entradas disponibles en la Platea Baja");
                        return;
                    }
                    break;
                case "platea alta":
                    if (entradasDisponiblesPlateaAlta > 0) {
                        precioBase = entrada.equalsIgnoreCase("estudiante") ? 9000 : 11000;
                        asientos[getPrimerAsientoDisponible("Platea Alta")] = "Ocupado";
                        entradasDisponiblesPlateaAlta--;
                    } else {
                        System.out.println("Lo siento, no hay entradas disponibles en la Platea Alta");
                        return;
                    }
                    break;
                case "palcos":
                    if (entradasDisponiblesPalcos > 0) {
                        precioBase = entrada.equalsIgnoreCase("estudiante") ? 6500 : 7200;
                        asientos[getPrimerAsientoDisponible("Palcos")] = "Ocupado";
                        entradasDisponiblesPalcos--;
                    } else {
                        System.out.println("Lo siento, no hay entradas disponibles en los Palcos");
                        return;
                    }
                    break;
                default:
                    System.out.println("Categoria invalida");
                    return;
            }

            double descuento = getDescuento(entrada);

            // Aplicar el descuento al total
            double total = precioBase - (precioBase * descuento);

            System.out.println("Boleta de la compra:");
            System.out.println("Ubicacion del asiento: " + categoria);
            System.out.println("Precio base de la entrada: $" + precioBase);
            System.out.println("Descuento aplicado: " + (descuento * 100) + "%");
            System.out.println("Precio final a pagar: $" + total);

            System.out.println("Desea comprar otra entrada? (si/no)");
            continuar = scanner.nextLine();

        } while (continuar.equalsIgnoreCase("si"));
    }

    // Método para los asientos
    public static void inicializarAsientos() {
        for (int i = 0; i < asientos.length; i++) {
            asientos[i] = "Disponible";
        }
    }

    // Método de verificación disponibilidad asientos
    public static void verDisponibilidad() {
        System.out.println("Disponibilidad de entradas:");
        System.out.println("Zona A - Zona VIP: " + entradasDisponiblesVIP + " entradas disponibles");
        System.out.println("Zona B - Platea Baja: " + entradasDisponiblesPlateaBaja + " entradas disponibles");
        System.out.println("Zona C - Platea Alta: " + entradasDisponiblesPlateaAlta + " entradas disponibles");
        System.out.println("Zona D - Palcos: " + entradasDisponiblesPalcos + " entradas disponibles");
    }

    // Método para obtener el asiento disponible en una categoría
    public static int getPrimerAsientoDisponible(String categoria) {
        for (int i = 0; i < asientos.length; i++) {
            if (asientos[i].equals("Disponible")) {
                System.out.println("Asiento asignado en " + categoria + " - Asiento " + (i + 1));
                return i;
            }
        }
        return -1; //Si no hay asientos disponibles en la categoria
    }

    //Método para obtener descuentos
    public static double getDescuento(String tipoEntrada) {
        switch (tipoEntrada) {
            case "estudiante":
                return 0.10;
            case "tercera edad":
                return 0.15;
            default:
                return 0.0;
        }
    }
}
