import java.util.concurrent.Callable;

public class SistemaSoporteV  implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "🧪 Soporte vital: presión y oxígeno dentro de parametros normales.";
    }
}
