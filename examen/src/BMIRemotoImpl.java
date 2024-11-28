import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BMIRemotoImpl extends UnicastRemoteObject implements BMIRemoto {

    protected BMIRemotoImpl() throws RemoteException {
        super();
    }

    @Override
    public double calcularBMI(double peso, double altura) throws RemoteException {
        if (altura <= 0) {
            throw new IllegalArgumentException("La altura debe ser mayor que 0");
        }
        return peso / (altura * altura); // Calcula el BMI y lo devuelve al cliente
    }

    @Override
    public String categoriaBMI(double bmi) throws RemoteException {
        String categoria;
        if (bmi < 18.5) {
            categoria = "Underweight";
        } else if (bmi < 24.9) {
            categoria = "Normal weight";
        } else if (bmi < 29.9) {
            categoria = "Overweight";
        } else {
            categoria = "Obese";
        }
        return categoria; // Devuelve la categoría según el valor del BMI
    }

    @Override
    public String tablaIMB() throws RemoteException {
        // Construcción de la tabla para enviar al cliente
        StringBuilder tabla = new StringBuilder();
        tabla.append("+-------------------+---------------------+\n");
        tabla.append("|     BMI Range     |     Classification |\n");
        tabla.append("+-------------------+---------------------+\n");
        tabla.append(String.format("| %-17s | %-19s |\n", "< 18.5", "Underweight"));
        tabla.append(String.format("| %-17s | %-19s |\n", "18.5 - 24.9", "Normal Weight"));
        tabla.append(String.format("| %-17s | %-19s |\n", "25 - 29.9", "Overweight"));
        tabla.append(String.format("| %-17s | %-19s |\n", ">= 30", "Obese"));
        tabla.append("+-------------------+---------------------+\n");
        return tabla.toString(); // Devuelve la tabla como un String al cliente
    }
}
