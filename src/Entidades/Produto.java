package Entidades;

public class Produto {
    String nome, categoria;
    int estoque, cod;
    double preco;
    
    public Produto(String nome, String categoria, int estoque, double preco){
        this.categoria = categoria;
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
    }
    
    public String getCategoria(){
        return categoria;
    }
    
    public void setCategoria(String cat){
        categoria = cat;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nom){
        nome = nom;
    }
    
    public int getEstoque(){
        return estoque;
    }
    
    public void setEstoque(int quant){
        estoque = quant;
    }
    
    public int getCod(){
        return cod;
    }
    
    public void setCod(int c){
        cod = c;
    }
    
    
    public double getPreco(){
        return preco;
    }
    
    public void setPreco(double pre){
        preco = pre;
    }
    
    
}
