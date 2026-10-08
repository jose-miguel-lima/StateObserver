public class PedidoSaiuParaEntrega extends PedidoEstado{

    private PedidoSaiuParaEntrega() {};
    private static PedidoSaiuParaEntrega instance = new PedidoSaiuParaEntrega();
    public static PedidoSaiuParaEntrega getInstance() {
        return instance;
    }

    @Override
    public String getEstado(){
        return "Pedido Saiu Para Entrega";
    }

    @Override
    public boolean entregue(Pedido pedido) {
        pedido.setEstado(PedidoEntregue.getInstance());
        return true;
    }
}
