import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args){
        List<Sucursal> sucursales = Arrays.asList(
                new Sucursal("Centro", Arrays.asList(
                        new Encuesta("Juan","El tiempo de espera fue largo",2),
                        new Encuesta("Martha", null,4),
                        new Encuesta("Karla", "No me explicaron bien el tramiento",3)
                )),
                new Sucursal("Norte",Arrays.asList(
                        new Encuesta("Norma","La atención fue buena, pero la limpieza puede mejorar",3),
                        new Encuesta("Willian",null,2)
                ))
        );

        sucursales.stream()
                .flatMap(sucursal ->
                        sucursal.getEncuestas().stream()
                                .filter(e -> e.getCalificacion() <= 3 )
                                .map(e -> e.getComentario()
                                        .map(com -> "Sucursal " + sucursal.getNombre() + ": Seguimiento a paciente con comentario: \"" + com + "\"")
                                )
                )
                .flatMap(Optional::stream)
                .forEach(System.out::println);
    }
}