public abstract class PedidoEstado {

    public abstract String getEstado();

    public boolean aceitar(Pedido pedido){
        return false;
    }

    public boolean cancelar(Pedido pedido){
        return false;
    }

    public boolean sairParaEntrega(Pedido pedido){
        return false;
    }

    public boolean entregue(Pedido pedido){
        return false;
    }

}
