import java.time.Duration;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SistemasCriticos {
    //nombres de avenidas y cruces
    private static final List<String> Avenidas = Arrays.asList("Michoacan","Morelos","Oaxaca","Veracruz","Puebla");
    private static final List<String>CRUCES = Arrays.asList("Norte","Sur","Este","Oeste");
    private static final Random RANDOM = new Random();

    public static  void main(String[] args) throws InterruptedException{

        //Creacion de los sensores de trafico con backpressure
        Flux<String> flujoTrafico = Flux.interval(Duration.ofMillis(500))
                .map(i ->{
                    int congestion = RANDOM.nextInt(101);//congestion de 0 al 100%
                    String avenida = Avenidas.get(RANDOM.nextInt(Avenidas.size()));
                    return new AbstractMap.SimpleEntry<>(congestion, avenida);
                })
                .onBackpressureBuffer(5)
                .filter(e -> e.getKey() > 70)
                .map(e -> "🚗 Alerta: Congestión del " + e.getKey() + " % en Avenida " + e.getValue());

        //Contaminación del aire
        Flux<String> flujoContaminacion = Flux.interval(Duration.ofMillis(600))
                .map(i -> RANDOM.nextInt(81))
                .filter(pm -> pm > 50)
                .map(pm -> "🌫️ Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)");

        // Accidentes viales
        Flux<String> flujoAccidentes = Flux.interval(Duration.ofMillis(800))
                .map(i -> {
                    String[] prioridades = {"Baja", "Media", "Alta"};
                    String prioridad = prioridades[RANDOM.nextInt(prioridades.length)];
                    return prioridad;
                })
                .filter(prioridad -> prioridad.equals("Alta")) // Solo accidentes críticos
                .map(prioridad -> "🚑 Emergencia vial: Accidente con prioridad Alta");

        // Trenes maglev (con delayElements para simular backpressure)
        Flux<String> flujoMaglev = Flux.interval(Duration.ofMillis(700))
                .map(i -> RANDOM.nextInt(11)) // Retraso entre 0 y 10 minutos
                .delayElements(Duration.ofMillis(200))
                .filter(retraso -> retraso > 5)
                .map(retraso -> "🚝 Tren maglev con retraso crítico: " + retraso + " minutos");

        // Semáforos inteligentes
        Flux<String> flujoSemaforos = Flux.create(sink -> {
            Map<String, AtomicInteger> contadorRojos = new HashMap<>();
            for (String cruce : CRUCES) {
                contadorRojos.put(cruce, new AtomicInteger(0));
            }
            Flux.interval(Duration.ofMillis(400))
                    .subscribe(i -> {
                        String cruce = CRUCES.get(RANDOM.nextInt(CRUCES.size()));
                        String[] estados = {"Verde", "Amarillo", "Rojo"};
                        String estado = estados[RANDOM.nextInt(estados.length)];
                        if (estado.equals("Rojo")) {
                            int cuenta = contadorRojos.get(cruce).incrementAndGet();
                            if (cuenta >= 3) {
                                sink.next("🚦 Semáforo en Rojo detectado 3 veces seguidas en cruce " + cruce);
                                contadorRojos.get(cruce).set(0); // Reinicia el contador
                            }
                        } else {
                            contadorRojos.get(cruce).set(0); // Reinicia si no es rojo
                        }
                    });
        });
        // Combina todos los flujos para poder detectar alertas globales
        Flux<String> flujoCombinado = Flux.merge(
                flujoTrafico.publishOn(Schedulers.parallel()),
                flujoContaminacion.publishOn(Schedulers.parallel()),
                flujoAccidentes.publishOn(Schedulers.parallel()),
                flujoMaglev.publishOn(Schedulers.parallel()),
                flujoSemaforos.publishOn(Schedulers.parallel())
        );

        // Ventana de 1 segundo para detectar eventos críticos simultáneos
        flujoCombinado.window(Duration.ofSeconds(1))
                .flatMap(ventana -> ventana.collectList())
                .subscribe(eventos -> {
                    if (eventos.size() >= 3) {
                        // Alerta global si hay 3 o más eventos críticos al mismo tiempo
                        System.out.println("🚨 Alerta global: Múltiples eventos críticos detectados en Meridian Prime");
                        eventos.forEach(System.out::println);
                    } else {
                        // Si no, solo muestra los eventos críticos individuales
                        eventos.forEach(System.out::println);
                    }
                });

        // Mantiene la aplicación corriendo por 20 segundos para la simulación
        Thread.sleep(20000);
    }
}
