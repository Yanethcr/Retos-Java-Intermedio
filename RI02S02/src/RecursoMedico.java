
import java.util.concurrent.locks.ReentrantLock;

public class RecursoMedico {
    private String nombre;
    private final ReentrantLock lock =  new ReentrantLock();

    //Constructor
    public RecursoMedico(String nombre){
        this.nombre = nombre;
    }

    public void usar(String profesional){
        lock.lock();
        try{
            System.out.println("👩‍⚕️" + profesional + " ha ingresado a " + nombre );
            Thread.sleep(1000);
            System.out.println("✅" + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

}
