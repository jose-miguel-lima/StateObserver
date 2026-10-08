import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente implements Observador {

    private final String nome;
    private final Pedido pedido;
    private final List<String> mensagens = new ArrayList<>();

    public Cliente(String nome, Pedido pedido){
        this.nome = nome;
        this.pedido = pedido;
    }

    @Override
    public void atualizar(){
        mensagens.add("Olá, " + nome + "! Seu pedido está: " + pedido.getNomeEstado());
    }

    public String getNome(){
        return this.nome;
    }

    public List<String> getMensagens(){
        return Collections.unmodifiableList(mensagens);
    }

    public String getUltimaMensagem(){
        return mensagens.isEmpty() ? null : mensagens.get(mensagens.size() - 1);
    }

}
