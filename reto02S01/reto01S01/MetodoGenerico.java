package reto01S01;

import java.util.List;

//clase que implementa el metodo generico y muestra las ordenes de produccion
public class MetodoGenerico {
    public static void mostrarOrdenes(List<? extends OrdenProduccion> ordenes) {
        for (OrdenProduccion orden : ordenes) {
            orden.mostrarResumen();
        }
    }

    // metodo para las ordenes personalizadas
    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada) {
                OrdenPersonalizada op = (OrdenPersonalizada) obj;
                System.out.println("✅ Orden " + op.getCodigo() + " ajustada con costo adicional de $" + costoAdicional);
            }
        }
    }
}
