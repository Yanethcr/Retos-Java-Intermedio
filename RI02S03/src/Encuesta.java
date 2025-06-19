import java.util.Optional;

public class Encuesta {
    private final String paciente;
    private final String comentario;
    private final int calificacion;

    //Constructor
    public Encuesta(String paciente, String comentario, int calificacion) {
        this.paciente = paciente;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }
    //metodo para obtener el comentario


   public Optional<String> getComentario(){ return Optional.ofNullable(comentario);}
    public String getPaciente(){ return paciente;}
    public int getCalificacion(){ return calificacion;}
}
