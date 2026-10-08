/**
 * Pedido é, ao mesmo tempo:
 *  - o Context do padrão State (delega o comportamento ao PedidoEstado atual);
 *  - o ConcreteSubject do padrão Observer (notifica os observadores a cada mudança de estado).
 *
 * O ponto de integração é o setEstado(): toda transição passa por ele,
 * então é ali que os observadores são avisados. Os estados concretos
 * não precisam saber que existem observadores.
 */
public class Pedido extends Sujeito {

    private String nome;
    private PedidoEstado pedidoEstado;

    public Pedido(){
        this.pedidoEstado = PedidoFeito.getInstance();
    }

    public Pedido(String nome){
        this();
        this.nome = nome;
    }

    public void setEstado(PedidoEstado pedidoEstado){
        this.pedidoEstado = pedidoEstado;
        notificarObservadores();
    }

    public PedidoEstado getEstado(){
        return pedidoEstado;
    }

    public boolean aceitar(){
        return pedidoEstado.aceitar(this);
    }

    public boolean cancelar(){
        return pedidoEstado.cancelar(this);
    }

    public boolean sairParaEntrega(){
        return pedidoEstado.sairParaEntrega(this);
    }

    public boolean entregue(){
        return pedidoEstado.entregue(this);
    }

    public String getNomeEstado(){
        return pedidoEstado.getEstado();
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
