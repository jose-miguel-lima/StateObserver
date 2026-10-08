public class PedidoFeito extends PedidoEstado{

    private PedidoFeito() {};
    private static PedidoFeito instance = new PedidoFeito();
    public static PedidoFeito getInstance() {
        return instance;
    }

    @Override
    public String getEstado(){
        return "Pedido Feito";
    }

    @Override
    public boolean aceitar(Pedido pedido){
        pedido.setEstado(PedidoEmExecucao.getInstance());
        return true;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(PedidoCancelado.getInstance());
        return true;
    }
}
