public class PedidoEmExecucao extends PedidoEstado{

    private PedidoEmExecucao() {};
    private static PedidoEmExecucao instance = new PedidoEmExecucao();
    public static PedidoEmExecucao getInstance() {
        return instance;
    }

    @Override
    public String getEstado(){
        return "Pedido Em Execução";
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(PedidoCancelado.getInstance());
        return true;
    }

    @Override
    public boolean sairParaEntrega(Pedido pedido) {
        pedido.setEstado(PedidoSaiuParaEntrega.getInstance());
        return true;
    }
}
