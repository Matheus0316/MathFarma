package Server;

import java.util.List;
import Entidades.Produto;
import Entidades.User;
import Entidades.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Repository {
    
    public void cadastrarUser(User u){
        Conexao con = new Conexao();
        String sql = "INSERT INTO USUARIO (id, nome, email, senha) VALUES (nextval('USUARIO_SEQ'), '"+ u.getNome() +"', '" + u.getEmail() +"', '" + u.getSenha() +"');";
        int res;
        try {
            res = con.executarSQL(sql);
            verificaIdUser(u.getEmail(), u.getSenha());
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
        String sql = "INSERT INTO PRODUTO (cod, nome, categoria, estoque, preco) VALUES (nextval('PROD_SEQ'), '" + p.getNome() +"', '" +p.getCategoria()+"', '" + p.getEstoque() +"', '"+ p.getPreco() +"');";
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
    
    public List<Produto> listarProdutos(int cat){
        List<Produto> lista = new LinkedList<>();
        Conexao con = new Conexao();
        String sql = "SELECT * FROM PRODUTO ORDER BY nome";
        ResultSet rs = null;
        
        try {
            rs = con.executaBusca(sql);
            
            while (rs.next()) {
                Produto p = new Produto(rs.getInt("cod"), rs.getString("nome"), rs.getInt("categoria"), 
                    rs.getInt("estoque"), rs.getDouble("preco"));
                if (p.getCategoria() == cat){
                    lista.add(p);
                }
                if (cat == 0)
                    lista.add(p);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return lista; 
    }
    
    public double getTotal(User user){
        double total = 0;
        Conexao con = new Conexao();
        String sql = "SELECT * FROM ITENS WHERE carrinho_id = "+ idCarrinho(user) +" ORDER BY produto_cod ";
        ResultSet rs = null;
        
        try {
            rs = con.executaBusca(sql);
            
            while (rs.next()) {
                    total += (rs.getInt("quantidade") * rs.getDouble("preco"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
    
    public List<Item> listarCarrinho(User user){
        List<Item> itens = new LinkedList<>();
        Conexao con = new Conexao();
        String sql = "SELECT * FROM ITENS WHERE carrinho_id = "+ idCarrinho(user) +" ORDER BY produto_cod ";
        ResultSet rs = null;
        
        try {
            rs = con.executaBusca(sql);
            
            while (rs.next()) {
                Item it = new Item(rs.getInt("id"), rs.getInt("carrinho_id"), rs.getInt("produto_cod"), rs.getInt("quantidade"),
                        (rs.getInt("quantidade") * rs.getDouble("preco")));
                    itens.add(it);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return itens;
    }
    
    public Produto getProd(int cod){
        Produto p;
        Conexao con = new Conexao();
        String sql = "SELECT * FROM Produto WHERE cod = "+ cod;
        ResultSet rs = null;
        
        try {
            rs = con.executaBusca(sql);
            
            while (rs.next()) {
                p = new Produto(rs.getInt("cod"), rs.getString("nome"), rs.getInt("categoria"), 
                    rs.getInt("estoque"), rs.getDouble("preco"));
                return p;
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void criarItem(int cod, int quantidade, User user){
        Produto p = getProd(cod);
        Conexao con = new Conexao();
        String sql = "INSERT INTO ITENS (id, Carrinho_id, produto_cod, quantidade, preco) VALUES (nextval('ITEM_SEQ')," + idCarrinho(user) + ", "+ cod +", "+ quantidade +", "+ p.getPreco() +");";
        int res;
        
        try {
            res = con.executarSQL(sql);
            removerEstoque(cod, quantidade);
        } catch (SQLException e) {
            e.printStackTrace();
            res = 0;
        }
        if (res > 0){
                JOptionPane.showMessageDialog(null, "Produto adicionado ao carrinho");
        }
        else{
            if (quantidade > 1){
                JOptionPane.showMessageDialog(null, "Não foi posivel adicionar os itens ao carrinho");
            }
            else{
                JOptionPane.showMessageDialog(null, "Não foi posivel adicionar o item ao carrinho");
            }
        }
    }
    
    public int idCarrinho(User u){
        int id;
        Conexao con = new Conexao();
        String sql = "SELECT * FROM CARRINHO WHERE id_user = "+ u.getId();
        ResultSet rs = null;
        
        try {
            rs = con.executaBusca(sql);
            
            while (rs.next()) {
                id = rs.getInt("id");
                return id;
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public void verificaIdUser(String email, String senha){
        Conexao con = new Conexao();
        String sql = "SELECT * FROM USUARIO";
        ResultSet rs = null;
        try{
            rs = con.executaBusca(sql);
            while(rs.next()){
                String em = rs.getString("email");
                String se = rs.getString("senha");
                int id = rs.getInt("id");
                if(email.equals(em) && senha.equals(se)){
                    criarCarrinho(id);
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void criarCarrinho(int id){
        Conexao con = new Conexao();
        String sql = "INSERT INTO CARRINHO (id, id_user) VALUES (nextval('CAR_SEQ'), '"+ id +"');";
        int res;
            try {
                res = con.executarSQL(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                res = 0;
            }
            if (res > 0){
                JOptionPane.showMessageDialog(null, "Carrinho criado com sucesso");

            }

            else{
                JOptionPane.showMessageDialog(null, "O carrinho não foi criado");
            }
    }
    
    public void removerEstoque(int cod, int quant){
        Conexao con = new Conexao();
        String sql = "UPDATE PRODUTO SET estoque = estoque - "+ quant +" WHERE cod = "+ cod +";";
        int res;
        try {
                res = con.executarSQL(sql);    
        }catch (Exception e) {
                e.printStackTrace();
                res = 0;
        }
    }
    
    public void editarEstoque(int cod, int quant){
        Conexao con = new Conexao();
        String sql = "UPDATE PRODUTO SET estoque = "+ quant +" WHERE cod = "+ cod +";";
        int res;
        try {
                res = con.executarSQL(sql);    
        }catch (Exception e) {
                e.printStackTrace();
                res = 0;
        }
    }
    
        public void editarEstoque(int cod, double preco){
        Conexao con = new Conexao();
        String sql = "UPDATE PRODUTO SET preco = "+ preco +" WHERE cod = "+ cod +";";
        int res;
        try {
                res = con.executarSQL(sql);    
        }catch (Exception e) {
                e.printStackTrace();
                res = 0;
        }
    }
    
    public void comprar(User user){
        Conexao con = new Conexao();
        String sql = "DELETE FROM ITENS WHERE carrinho_id = "+ idCarrinho(user) +";";
        int res;
        try {
                res = con.executarSQL(sql);    
        }catch (Exception e) {
                e.printStackTrace();
                res = 0;
        }
        if (res > 0){
                JOptionPane.showMessageDialog(null, "Compra realizada com sucesso");

        }

            else{
                JOptionPane.showMessageDialog(null, "Não foi possivel comprar");
        }
    }
}

