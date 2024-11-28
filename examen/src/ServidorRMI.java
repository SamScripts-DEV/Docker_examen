import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {
    public static void main(String[] args) throws Exception {
        try {
            BMIRemoto interfaz = new BMIRemotoImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ClienteRemoto", interfaz);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
