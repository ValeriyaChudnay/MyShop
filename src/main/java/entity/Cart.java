package entity;

import entity.product.Shoes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {
    private Map<Shoes,Integer> cart;

    public Map<Shoes, Integer> getCart() {
        return cart;
    }

    public Cart() {
        this.cart = new HashMap<>();
    }
    public void add(Shoes shoes,int amount){
        cart.put(shoes,amount);
    }
    public void remove(Shoes shoes){
        cart.remove(shoes);
    }
    public void clear(){
        cart.clear();
    }
    public List<Shoes> getAll(){
        return (List<Shoes>) cart.keySet();
    }
    public int getAmount(){
       return cart.values().stream().mapToInt(Integer::intValue).sum();
    }
    public double getSum(){
        return cart.entrySet().stream().mapToDouble(e-> (e.getValue()*e.getKey().getPrice())).sum();

    }

    public void putAll(Cart cartBeforeLogIn) {
        cart.putAll(cartBeforeLogIn.getCart());
    }

}
