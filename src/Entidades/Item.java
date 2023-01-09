/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Pichau
 */
public class Item {
    private int id, carrinhoId, produtoCod, quantidade;
    private double preco;
    
    public Item(int id, int carrinhoId, int produtoCod, int quantidade, double preco){
    this.id = id;
    this.carrinhoId = carrinhoId;
    this.produtoCod = produtoCod;
    this.quantidade = quantidade;
    this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public int getCarrinhoId() {
        return carrinhoId;
    }

    public int getProdutoCod() {
        return produtoCod;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }
    
    
}
