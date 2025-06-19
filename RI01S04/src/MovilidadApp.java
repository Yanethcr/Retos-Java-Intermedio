import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MovilidadApp {

    public CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🚦 Calculando ruta...");
                int latencia = ThreadLocalRandom.current().nextInt(2000, 3001);
                TimeUnit.MILLISECONDS.sleep(latencia);
                return "Centro -> Norte";
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrumpido al calcular ruta");
            }
        });
    }

    public CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("💰 Estimando tarifa...");
                int latencia = ThreadLocalRandom.current().nextInt(1000, 2001);
                TimeUnit.MILLISECONDS.sleep(latencia);
                return 75.50;
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrumpido al estimar tarifa");
            }
        });
    }

    public void procesarSolicitud() {
        CompletableFuture<String> rutaFuture = calcularRuta()
                .exceptionally(e -> {
                    System.out.println("❌ Error en ruta: " + e.getMessage());
                    return "Ruta no disponible";
                });

        CompletableFuture<Double> tarifaFuture = estimarTarifa()
                .exceptionally(e -> {
                    System.out.println("❌ Error en tarifa: " + e.getMessage());
                    return 0.0;
                });

        rutaFuture.thenCombine(tarifaFuture, (ruta, tarifa) -> {
                    return "✅ 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa;
                }).thenAccept(System.out::println)
                .join();
    }

    public static void main(String[] args){
        MovilidadApp app = new MovilidadApp();
        app.procesarSolicitud();
    }
}