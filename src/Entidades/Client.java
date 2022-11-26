package Entidades;

public class Client extends User{
    
    public Client(int id, String nome, String email, String senha){
        super(id, nome, email, senha);
    }
    
    public Client(String nome, String email, String senha){
        super(nome, email, senha);
    }
    
    public void comprar(Produto p, int quant){
        p.setEstoque(p.getEstoque() - quant);
    }
}
