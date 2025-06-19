
package reto01S01;

//CREACION DE LA CLASE ABTRACTA 
abstract class OrdenProduccion {
    private String codigo;
    private int cantidad;

    // Constructor de la clase OrdenProduccion
    public OrdenProduccion(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Metodo abstracto mostrar resumen
    public abstract void mostrarResumen();
}