import java.util.List;

public class Sucursal {
    private final String nombre;
    private final List<Encuesta> encuestas;

    //Constructores
    public Sucursal(String nombre, List<Encuesta> encuestas) {
        this.nombre = nombre;
        this.encuestas = encuestas;
    }
    //getters

    public List<Encuesta> getEncuestas() {
        return encuestas;
    }

    public String getNombre() {
        return nombre;
    }
}
