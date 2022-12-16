package Entidades;

public class Produto {
    String nome;
    int categoria, estoque, cod;
    double preco;
    
    public Produto(String nome, int categoria, int estoque, double preco){
        this.categoria = categoria;
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
    }
    
    public Produto(int cod, String nome, int categoria, int estoque, double preco){
        this.cod = cod;
        this.categoria = categoria;
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
    }
    
    public String getCategoria(){
        if (categoria == 1){
            return "Remédios";
        }
        if (categoria == 2){
            return "Higiene";
        }
        if (categoria == 3){
            return "Chocolates";
        }
        if (categoria == 4){
            return "Material escolar";
        }
        else{
            return null;
        }
        
    }
    
    public void setCategoria(int cat){
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

    public void getNome(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
