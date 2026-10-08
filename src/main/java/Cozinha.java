import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cozinha implements Observador {

    private final Pedido pedido;
    private final List<String> acoes = new ArrayList<>();

    public Cozinha(Pedido pedido){
        this.pedido = pedido;
    }

    @Override
    public void atualizar(){
        PedidoEstado estado = pedido.getEstado();

        if (estado == PedidoEmExecucao.getInstance()) {
            acoes.add("Iniciar preparo");
        } else if (estado == PedidoCancelado.getInstance()) {
            acoes.add("Descartar pedido");
        }
    }

    public List<String> getAcoes(){
        return Collections.unmodifiableList(acoes);
    }

}
