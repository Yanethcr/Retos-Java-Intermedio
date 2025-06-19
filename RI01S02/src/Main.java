import java.util.concurrent.*;
public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println("🚀 Simulación de misión espacial iniciada...");
/*
    Forma en la que indica el Git de Bedu pero por alguna razón
    lo imprime de una forma establecida sin respetar el sleep
        //Creación de los hilos para ejecutar las clases
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Future<String> nav = executor.submit(new SistemaNavegacion());
        Future<String> soporte = executor.submit(new SistemaSoporteV());
        Future<String> control = executor.submit(new SistemaControlT());
        Future<String> comunicaciones = executor.submit(new SistemaComunicaciones());

        //Impresión de los resultados con executor
        System.out.println(comunicaciones.get());
        System.out.println(soporte.get());
        System.out.println(control.get());
        System.out.println(nav.get());

 */
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletionService<String> servicio = new ExecutorCompletionService<>(executor);
        
        servicio.submit(new SistemaNavegacion());
        servicio.submit(new SistemaSoporteV());
        servicio.submit(new SistemaControlT());
        servicio.submit(new SistemaComunicaciones());

        // Imprimir resultados en el orden en que terminan
        for (int i = 0; i < 4; i++) {
            Future<String> resultado = servicio.take(); // Espera al siguiente que termine
            System.out.println(resultado.get());
        }

        executor.shutdown();
        System.out.println("✅ Todos los sistemas reportan estado operativo");

    }
}
