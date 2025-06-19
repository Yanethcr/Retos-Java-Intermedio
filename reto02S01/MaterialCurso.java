package reto02S01;

abstract class MaterialCurso {
    // Declaracion de los atributos
    protected String titulo;
    protected String autor;

    // Creacion del constructor
    public MaterialCurso(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public abstract void mostrarDetalle();
}
