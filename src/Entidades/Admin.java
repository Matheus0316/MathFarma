package Entidades;


public class Admin extends User{
    
    public Admin (int id, String nome, String email, String senha){
        super(id, nome, email, senha);
    }
    
    public void adicionar(Produto p, int quant){
        p.setEstoque(p.getEstoque() + quant);
    }
}
