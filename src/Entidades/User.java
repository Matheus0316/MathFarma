package Entidades;

public abstract class User {
    private int id;
    private String nome, email, senha;
    
    protected User (int id, String nome, String email, String senha){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    protected User (String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
