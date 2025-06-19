package reto01S01;

public class OrderPrototipo extends OrdenProduccion {
    private String FaseDesarrollo;

    public OrderPrototipo(String codigo, int cantidad, String faseDesarrollo) {
        super(codigo, cantidad);
        this.FaseDesarrollo = faseDesarrollo;
    }

    // getter para la fase de desarrollo
    public String getFaseDesarrollo() {
        return FaseDesarrollo;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("🧪 OrdenPrototipo - Código: " + getCodigo() + " - Cantidad: " + getCantidad()
                + " - Fase: " + getFaseDesarrollo());
    }
}
