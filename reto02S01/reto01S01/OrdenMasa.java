package reto01S01;

//Creacion de la subclase OrdenMasa que hereda de OrdenProduccion 
public class OrdenMasa extends OrdenProduccion {
    public OrdenMasa(String codigo, int cantidad) {
        super(codigo, cantidad);
    }

    @Override
    public void mostrarResumen() {
        System.out.println("🔩 OrdenMasa - Código: " + getCodigo() + " - Cantidad: " + getCantidad());
    }

}
