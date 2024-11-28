import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClienteRMI {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            BMIRemoto interfaz = (BMIRemoto) registry.lookup("ClienteRemoto");
            Scanner scanner = new Scanner(System.in);

            System.out.println("=== Bienvenido al Servicio de Cálculo de BMI ===");
            boolean continuar = true;

            while (continuar) {
                try {
                    System.out.println("\nSeleccione una opción:");
                    System.out.println("1: Calcular BMI");
                    System.out.println("2: Saber mi categoría BMI");
                    System.out.println("3: Imprimir tabla de categorías BMI");
                    System.out.println("4: Salir");

                    System.out.print("Opción: ");
                    int opcion = scanner.nextInt();

                    if (opcion == 4) {
                        System.out.println("Gracias por usar el servicio de cálculo de BMI. ¡Adiós!");
                        continuar = false;
                        break;
                    }

                    if (opcion == 1) {
                        System.out.print("Ingrese su peso en kilogramos: ");
                        double peso = scanner.nextDouble();

                        System.out.print("Ingrese su altura en metros: ");
                        double altura = scanner.nextDouble();

                        double bmi = interfaz.calcularBMI(peso, altura); // Calcula el BMI en el servidor
                        System.out.println("========================================");
                        System.out.printf("Resultado:\nBMI: %.2f\n", bmi);
                        System.out.println("========================================\n");
                    } else if (opcion == 2) {
                        System.out.print("Ingrese su BMI: ");
                        double bmi = scanner.nextDouble();

                        String categoria = interfaz.categoriaBMI(bmi); // Obtiene la categoría desde el servidor
                        System.out.println("========================================");
                        System.out.printf("Categoría: %s\n", categoria);
                        System.out.println("========================================\n");
                    } else if (opcion == 3) {
                        String tabla = interfaz.tablaIMB(); // Obtiene la tabla del servidor
                        System.out.println("========================================");
                        System.out.println(tabla); // Muestra la tabla completa
                        System.out.println("========================================\n");
                    } else {
                        System.out.println("Opción no válida. Por favor, elija una opción del 1 al 4.");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor ingrese un número.");
                    scanner.nextLine(); // Limpiar la entrada
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Ocurrió un error en el cliente:");
            e.printStackTrace();
        }
    }
}
