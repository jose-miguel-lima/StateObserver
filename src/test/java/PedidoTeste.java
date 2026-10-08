import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PedidoTeste {

    Pedido pedido;

    @BeforeEach
    public void setUp(){
        pedido = new Pedido();
    }

    // Pedido Feito

    @Test
    public void deveAceitarPedidoFeito(){
        pedido.setEstado(PedidoFeito.getInstance());
        assertTrue(pedido.aceitar());
    }

    @Test
    public void deveCancelarPedidoFeito(){
        pedido.setEstado(PedidoFeito.getInstance());
        assertTrue(pedido.cancelar());
    }

    @Test
    public void naoDeveSairParaEntregaPedidoFeito(){
        pedido.setEstado(PedidoFeito.getInstance());
        assertFalse(pedido.sairParaEntrega());
    }

    @Test
    public void naoDeveEntregarPedidoFeito(){
        pedido.setEstado(PedidoFeito.getInstance());
        assertFalse(pedido.entregue());
    }

    // Pedido Em Execução

    @Test
    public void naoDeveAceitarPedidoEmExecucao(){
        pedido.setEstado(PedidoEmExecucao.getInstance());
        assertFalse(pedido.aceitar());
    }

    @Test
    public void deveCancelarPedidoEmExecucao(){
        pedido.setEstado(PedidoEmExecucao.getInstance());
        assertTrue(pedido.cancelar());
    }

    @Test
    public void deveSairParaEntregaPedidoEmExecucaoDeve(){
        pedido.setEstado(PedidoEmExecucao.getInstance());
        assertTrue(pedido.sairParaEntrega());
    }

    @Test
    public void naoDeveEntregarPedidoEmExecucao(){
        pedido.setEstado(PedidoEmExecucao.getInstance());
        assertFalse(pedido.entregue());
    }

    //Pedido Cancelado

    @Test
    public void naoDeveAceitarPedidoCancelado(){
        pedido.setEstado(PedidoCancelado.getInstance());
        assertFalse(pedido.aceitar());
    }

    @Test
    public void naoDeveCancelarPedidoCancelado(){
        pedido.setEstado(PedidoCancelado.getInstance());
        assertFalse(pedido.cancelar());
    }

    @Test
    public void naoDeveSairParaEntregaPedidoCancelado(){
        pedido.setEstado(PedidoCancelado.getInstance());
        assertFalse(pedido.sairParaEntrega());
    }

    @Test
    public void naoDeveEntregarPedidoCancelado(){
        pedido.setEstado(PedidoCancelado.getInstance());
        assertFalse(pedido.entregue());
    }

    //Pedido Saiu Para Entrega
    @Test
    public void naoDeveAceitarPedidoQueSaiuParaEntrega(){
        pedido.setEstado(PedidoSaiuParaEntrega.getInstance());
        assertFalse(pedido.aceitar());
    }

    @Test
    public void naoDeveCancelarPedidoQueSaiuParaEntrega(){
        pedido.setEstado(PedidoSaiuParaEntrega.getInstance());
        assertFalse(pedido.cancelar());
    }

    @Test
    public void naoDeveSairParaEntregaPedidoQueSaiuParaEntrega(){
        pedido.setEstado(PedidoSaiuParaEntrega.getInstance());
        assertFalse(pedido.sairParaEntrega());
    }

    @Test
    public void deveEntregarPedidoQueSaiuParaEntrega(){
        pedido.setEstado(PedidoSaiuParaEntrega.getInstance());
        assertTrue(pedido.entregue());
    }

    //Pedido Entregue
    @Test
    public void naoDeveAceitarPedidoEntregue(){
        pedido.setEstado(PedidoEntregue.getInstance());
        assertFalse(pedido.aceitar());
    }

    @Test
    public void naoDeveCancelarPedidoEntregue(){
        pedido.setEstado(PedidoEntregue.getInstance());
        assertFalse(pedido.cancelar());
    }

    @Test
    public void naoDeveSairParaEntregaPedidoEntregue(){
        pedido.setEstado(PedidoEntregue.getInstance());
        assertFalse(pedido.sairParaEntrega());
    }

    @Test
    public void naoDeveEntregarPedidoEntregue(){
        pedido.setEstado(PedidoEntregue.getInstance());
        assertFalse(pedido.entregue());
    }
}
