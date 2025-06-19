package reto01S01;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // creacion de los objetos de las subcalses
        List<OrdenMasa> ordenesMasa = new ArrayList<>();
        ordenesMasa.add(new OrdenMasa("A123", 500));
        ordenesMasa.add(new OrdenMasa("A124", 750));

        List<OrdenPersonalizada> ordenesPersonalizadas = new ArrayList<>();
        ordenesPersonalizadas.add(new OrdenPersonalizada("P456", 100, "ClienteX"));
        ordenesPersonalizadas.add(new OrdenPersonalizada("P789", 150, "ClienteY"));

        List<OrderPrototipo> ordenesPrototipos = new ArrayList<>();
        ordenesPrototipos.add(new OrderPrototipo("T789", 10, "Diseño"));
        ordenesPrototipos.add(new OrderPrototipo("T790", 5, "Pruebas"));

        System.out.println("📋 Órdenes registradas: ");
        MetodoGenerico.mostrarOrdenes(ordenesMasa);
        System.out.println();
        MetodoGenerico.mostrarOrdenes(ordenesPersonalizadas);
        System.out.println();
        MetodoGenerico.mostrarOrdenes(ordenesPrototipos);
        System.out.println();

        // Procesar las ordenes personalizadas
        System.out.println("💰Procesando órdenes personalizadas... ");
        int costoAdicional = 200; // o el valor que desees
        MetodoGenerico.procesarPersonalizadas(ordenesPersonalizadas, costoAdicional);

        // Resumen total de órdenes
        System.out.println();
        System.out.println("📊 Resumen total de órdenes:");
        System.out.println("🔧 Producción en masa: " + ordenesMasa.size());
        System.out.println("🛠 Personalizadas: " + ordenesPersonalizadas.size());
        System.out.println("🧪 Prototipos: " + ordenesPrototipos.size());

    }
}
