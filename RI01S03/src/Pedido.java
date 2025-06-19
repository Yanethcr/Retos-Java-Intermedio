import java.util.Optional;

public class Pedido {
    private String cliente;
    private String tipoEntrega;
    private String telefono;

    //Constructor
    public Pedido(String cliente, String tipoEntrega, String telefono){
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    //getter para obtener el tipo de entrega


    public String getTipoEntrega() {
        return tipoEntrega;
    }

    //opcion para que el telefono pueda ser null
    public Optional<String> getTelefono(){
        return Optional.ofNullable(telefono);
    }
}
