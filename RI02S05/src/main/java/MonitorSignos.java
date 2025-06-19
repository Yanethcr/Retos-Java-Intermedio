
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;

public class MonitorSignos {

    static class EventoSignos {
        int pacienteId;
        int fc; // Frecuencia cardíaca
        int paSistolica;
        int paDiastolica;
        int spo2;

        EventoSignos(int pacienteId, int fc, int paSistolica, int paDiastolica, int spo2) {
            this.pacienteId = pacienteId;
            this.fc = fc;
            this.paSistolica = paSistolica;
            this.paDiastolica = paDiastolica;
            this.spo2 = spo2;
        }
    }

    static EventoSignos generarEvento(int pacienteId) {
        Random r = new Random();
        int fc = 40 + r.nextInt(100); // 40-139
        int paS = 80 + r.nextInt(80); // 80-159
        int paD = 50 + r.nextInt(50); // 50-99
        int spo2 = 85 + r.nextInt(15); // 85-99
        return new EventoSignos(pacienteId, fc, paS, paD, spo2);
    }

    static Flux<Alerta> flujoPaciente(int pacienteId) {
        return Flux.interval(Duration.ofMillis(300))
                .map(tick -> generarEvento(pacienteId))
                .flatMap(evento -> {
                    // Prioridad: FC > PA > SpO2
                    if (evento.fc < 50 || evento.fc > 120) {
                        return Flux.just(new Alerta(pacienteId, "FC crítica: " + evento.fc + " bpm"));
                    } else if (evento.paSistolica < 90 || evento.paDiastolica < 60 ||
                            evento.paSistolica > 140 || evento.paDiastolica > 90) {
                        return Flux.just(new Alerta(pacienteId, "PA crítica: " + evento.paSistolica + "/" + evento.paDiastolica + " mmHg"));
                    } else if (evento.spo2 < 90) {
                        return Flux.just(new Alerta(pacienteId, "SpO2 baja: " + evento.spo2 + "%"));
                    }
                    return Flux.empty();
                });
    }

    static class Alerta {
        int pacienteId;
        String mensaje;

        Alerta(int pacienteId, String mensaje) {
            this.pacienteId = pacienteId;
            this.mensaje = mensaje;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Flux<Alerta> flujoTotal = Flux.merge(
                        flujoPaciente(1),
                        flujoPaciente(2),
                        flujoPaciente(3)
                )
                .publishOn(Schedulers.parallel())
                .delayElements(Duration.ofSeconds(1)); // Backpressure: procesa 1 alerta por segundo

        flujoTotal.subscribe(alerta ->
                System.out.println("⚠️ Paciente " + alerta.pacienteId + " - " + alerta.mensaje)
        );

        // Mantener la app corriendo
        Thread.sleep(20000);
    }
}