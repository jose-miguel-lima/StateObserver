import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PedidoObservadoresTeste {

    Pedido pedido;
    Cliente cliente;
    Cozinha cozinha;

    @BeforeEach
    public void setUp(){
        pedido = new Pedido("Pizza");
        cliente = new Cliente("Maria", pedido);
        cozinha = new Cozinha(pedido);
        pedido.adicionarObservador(cliente);
        pedido.adicionarObservador(cozinha);
    }

    // Cadastro

    @Test
    public void deveTerOsObservadoresCadastrados(){
        assertEquals(2, pedido.getQuantidadeObservadores());
    }

    @Test
    public void deveRemoverObservador(){
        pedido.removerObservador(cliente);
        assertEquals(1, pedido.getQuantidadeObservadores());
    }

    // Criação do pedido

    @Test
    public void pedidoRecemCriadoNaoDeveNotificar(){
        Pedido novo = new Pedido();
        Cliente outro = new Cliente("João", novo);
        novo.adicionarObservador(outro);
        assertTrue(outro.getMensagens().isEmpty());
        assertNull(outro.getUltimaMensagem());
    }

    // Transições válidas notificam

    @Test
    public void deveNotificarClienteQuandoPedidoForAceito(){
        pedido.aceitar();
        assertEquals("Olá, Maria! Seu pedido está: Pedido Em Execução", cliente.getUltimaMensagem());
    }

    @Test
    public void deveNotificarClienteQuandoPedidoForCancelado(){
        pedido.cancelar();
        assertEquals("Olá, Maria! Seu pedido está: Pedido Cancelado", cliente.getUltimaMensagem());
    }

    @Test
    public void deveNotificarClienteQuandoPedidoSairParaEntrega(){
        pedido.setEstado(PedidoEmExecucao.getInstance());
        pedido.sairParaEntrega();
        assertEquals("Olá, Maria! Seu pedido está: Pedido Saiu Para Entrega", cliente.getUltimaMensagem());
    }

    @Test
    public void deveNotificarClienteQuandoPedidoForEntregue(){
        pedido.setEstado(PedidoSaiuParaEntrega.getInstance());
        pedido.entregue();
        assertEquals("Olá, Maria! Seu pedido está: Pedido Entregue", cliente.getUltimaMensagem());
    }

    @Test
    public void deveNotificarEmCadaEtapaDoFluxoCompleto(){
        pedido.aceitar();
        pedido.sairParaEntrega();
        pedido.entregue();

        assertEquals(List.of(
                "Olá, Maria! Seu pedido está: Pedido Em Execução",
                "Olá, Maria! Seu pedido está: Pedido Saiu Para Entrega",
                "Olá, Maria! Seu pedido está: Pedido Entregue"
        ), cliente.getMensagens());
    }

    @Test
    public void setEstadoDiretoTambemDeveNotificar(){
        pedido.setEstado(PedidoEmExecucao.getInstance());
        assertEquals(1, cliente.getMensagens().size());
    }

    // Transições inválidas NÃO notificam

    @Test
    public void naoDeveNotificarQuandoOperacaoForInvalida(){
        assertFalse(pedido.sairParaEntrega());   // Pedido Feito não pode sair para entrega
        assertFalse(pedido.entregue());          // nem ser entregue
        assertTrue(cliente.getMensagens().isEmpty());
        assertTrue(cozinha.getAcoes().isEmpty());
    }

    @Test
    public void naoDeveNotificarAposEstadoFinal(){
        pedido.cancelar();
        assertEquals(1, cliente.getMensagens().size());

        assertFalse(pedido.aceitar());
        assertFalse(pedido.cancelar());
        assertEquals(1, cliente.getMensagens().size());
    }

    // Observadores reagem de formas diferentes

    @Test
    public void cozinhaDeveIniciarPreparoQuandoPedidoForAceito(){
        pedido.aceitar();
        assertEquals(List.of("Iniciar preparo"), cozinha.getAcoes());
    }

    @Test
    public void cozinhaDeveDescartarPedidoQuandoForCancelado(){
        pedido.cancelar();
        assertEquals(List.of("Descartar pedido"), cozinha.getAcoes());
    }

    @Test
    public void cozinhaDeveIgnorarEstadosQueNaoSaoDelaMasClienteNao(){
        pedido.aceitar();
        pedido.sairParaEntrega();
        pedido.entregue();

        assertEquals(List.of("Iniciar preparo"), cozinha.getAcoes());
        assertEquals(3, cliente.getMensagens().size());
    }

    // Remoção e independência

    @Test
    public void observadorRemovidoNaoDeveMaisSerNotificado(){
        pedido.aceitar();
        pedido.removerObservador(cliente);
        pedido.sairParaEntrega();

        assertEquals(1, cliente.getMensagens().size());
        assertEquals("Olá, Maria! Seu pedido está: Pedido Em Execução", cliente.getUltimaMensagem());
    }

    @Test
    public void pedidosDiferentesNaoDevemNotificarObservadoresDeOutros(){
        Pedido outroPedido = new Pedido("Hambúrguer");
        Cliente outroCliente = new Cliente("João", outroPedido);
        outroPedido.adicionarObservador(outroCliente);

        pedido.aceitar();

        assertEquals(1, cliente.getMensagens().size());
        assertTrue(outroCliente.getMensagens().isEmpty());
    }
}
