package reto01S01;

public class OrdenPersonalizada extends OrdenProduccion {
    private String cliente;

    // Constructor de la clase OrdenPersonalizada hereda de OrdenProduccion
    public OrdenPersonalizada(String codigo, int cantidad, String cliente) {
        super(codigo, cantidad);
        this.cliente = cliente;
    }

    // getter para el cliente
    public String getCliente() {
        return cliente;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("🛠️ OrdenPersonalizada - Código: " + getCodigo() + " - Cantidad: " + getCantidad()
                + " - Cliente: " + getCliente());
    }
}