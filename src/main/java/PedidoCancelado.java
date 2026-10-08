public class PedidoCancelado extends PedidoEstado{

    private PedidoCancelado() {};
    private static PedidoCancelado instance = new PedidoCancelado();
    public static PedidoCancelado getInstance() {
        return instance;
    }

    @Override
    public String getEstado(){
        return "Pedido Cancelado";
    }

}
