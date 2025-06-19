import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args){
        List<Pedido> pedidos = Arrays.asList(
                new Pedido("Alejandro", "domicilio","2875632987"),
                new Pedido("Luisa","Local",null),
                new Pedido("Karina","Local",null),
                new Pedido("Jose", "domicilio", "5553645789")
                );
        pedidos.stream()
                .filter(p -> "domicilio".equalsIgnoreCase(p.getTipoEntrega()))
                .map(Pedido::getTelefono)
                .flatMap(Optional::stream)
                .map(tel -> "📞 Confirmación enviada al número: " + tel)
                .forEach(System.out::println);
    }
}
