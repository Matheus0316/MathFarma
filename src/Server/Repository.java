package Server;

import java.util.List;
import Entidades.Produto;
import Entidades.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Repository {
    
    public void cadastrarUser(User u){
        Conexao con = new Conexao();
        String sql = "INSERT INTO USUARIO (id, nome, email, senha) VALUES (nextval('USUARIO_SEQ'), '"+ u.getNome() +"', '" + u.getEmail() +"', '" + u.getSenha() +"')";
        int res;
        try {
            res = con.executarSQL(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            res = 0;
        }
        if (res > 0){
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        }
        
        else{
            JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
        }
        
        
    }
    
    public void cadastrarProd(Produto p){
        Conexao con = new Conexao();
        String sql = "INSERT INTO PRODUTO (cod, nome, categoria, estoque, preco) VALUES (nextval('PROD_SEQ'), '" + p.getNome() +"', '" +p.getCategoria()+"', '" + p.getEstoque() +"', '"+ p.getPreco() +"')";
        int res;
         
        try {
            res = con.executarSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        
        if (res > 0){
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
            
        }
        
        else{
            JOptionPane.showMessageDialog(null, "Produto não cadastrado");
        }
    }
    
    public List<Produto> listarProdutos(){
         List<Produto> lista = new LinkedList<>();
        Conexao con = new Conexao();
        String sql = "SELECT * FROM PRODUTO ORDER BY nome";
        ResultSet rs = null;
        
        try {
            rs = con.executaBusca(sql);
            
            while (rs.next()) {
                Produto p = new Produto(rs.getString("nome"), rs.getInt("categoria"), 
                    rs.getInt("estoque"), rs.getDouble("preco"));
                lista.add(p);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return lista; 
    }
    
}
