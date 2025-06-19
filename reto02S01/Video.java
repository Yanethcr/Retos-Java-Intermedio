package reto02S01;

public class Video extends MaterialCurso {
    // Declaracion de los atributos
    private int duracion; // Duracion en minutos

    // Creacion del constructor
    public Video(String titulo, String autor, int duracion) {
        super(titulo, autor);
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("📹 Video: " + titulo + ", Autor: " + autor + ", Duración: " + duracion + " minutos");
    }

}
