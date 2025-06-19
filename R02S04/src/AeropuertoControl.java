import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AeropuertoControl {

    public CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
                boolean disponible = ThreadLocalRandom.current().nextInt(100) < 80; // 80% probabilidad
                System.out.println("🛣️ Pista disponible: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar pista");
            }
        });
    }

    public CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
                boolean favorable = ThreadLocalRandom.current().nextInt(100) < 85; // 85% probabilidad
                System.out.println("🌦️ Clima favorable: " + favorable);
                return favorable;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar clima");
            }
        });
    }

    public CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
                boolean despejado = ThreadLocalRandom.current().nextInt(100) < 90; // 90% probabilidad
                System.out.println("🚦 Tráfico aéreo despejado: " + despejado);
                return despejado;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar tráfico aéreo");
            }
        });
    }

    public CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
                boolean disponible = ThreadLocalRandom.current().nextInt(100) < 95; // 95% probabilidad
                System.out.println("👷‍♂️ Personal disponible: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar personal de tierra");
            }
        });
    }

    public void autorizarAterrizaje() {
        System.out.println("🛫 Verificando condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pista = verificarPista().exceptionally(e -> {
            System.out.println("❌ Error en pista: " + e.getMessage());
            return false;
        });
        CompletableFuture<Boolean> clima = verificarClima().exceptionally(e -> {
            System.out.println("❌ Error en clima: " + e.getMessage());
            return false;
        });
        CompletableFuture<Boolean> trafico = verificarTraficoAereo().exceptionally(e -> {
            System.out.println("❌ Error en tráfico aéreo: " + e.getMessage());
            return false;
        });
        CompletableFuture<Boolean> personal = verificarPersonalTierra().exceptionally(e -> {
            System.out.println("❌ Error en personal de tierra: " + e.getMessage());
            return false;
        });

        CompletableFuture.allOf(pista, clima, trafico, personal)
                .thenRun(() -> {
                    boolean condicionesOptimas = pista.join() && clima.join() && trafico.join() && personal.join();
                    if (condicionesOptimas) {
                        System.out.println("\n🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
                    } else {
                        System.out.println("\n🚫 Aterrizaje denegado: condiciones no óptimas.");
                    }
                }).join();
    }

    public static void main(String[] args) {
        AeropuertoControl control = new AeropuertoControl();
        control.autorizarAterrizaje();
    }
}
