import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("🏥 Iniciando acceso a la Sala de cirugía... ");

        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía...");

        //tareas de cada medico con runnable
        Runnable draJuanita = () -> salaCirugia.usar("Dra.Juanita");
        Runnable drCarlos = () -> salaCirugia.usar("Dr.Carlos");
        Runnable drOscar = () -> salaCirugia.usar("Dr.Oscar");
        Runnable enfermera = () -> salaCirugia.usar("Enfermera Meredith");

        //Executor con los 4 hilos para ejecutar las tareas
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.execute(draJuanita);
        executor.execute(drCarlos);
        executor.execute(drOscar);
        executor.execute(enfermera);

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}

