package Entidades;


public class Admin extends User{
    
    public Admin (int id, String nome, String email, String senha){
        super(id, nome, email, senha);
    }
    
    public Admin (String nome, String email, String senha){
        super(nome, email, senha);
    }
    
    public void novoProduto(String nom, String cat, int est, double pre){
        Produto p = new Produto(nom, cat, est, pre);
    }
    
    public void adicionar(Produto p, int quant){
        p.setEstoque(p.getEstoque() + quant);
    }
}
