/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import entities.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.Collation;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.ProductFacade;

/**
 *
 * @author SE151470 Tran Vuong Anh Tuan
 */
public class Cart {
    
    ProductFacade pf = lookupProductFacadeBean();
    Map<Integer,Item> map = null;
    
    public Cart() {
        map = new HashMap();
    }
    
    public void add(int id, int quantity) {
        //tim item trong cart
        Item item = map.get(id);
        //neu item chua co trong cart thi tao moi item va them vao cart
        if (item == null) {
            Product product = pf.find(id);
            item = new Item(product.getId(), product.getDescription(), product.getPrice(), product.getDiscount(), quantity);
            map.put(id, item);
        } else { // neu da co item trong cart thi cap nhat quantity
            item.setQuantity(item.getQuantity() + quantity);
        }
    }
    
   
    
    public void delete(int id) {
        map.remove(id);
    }
    
    public void update(int id, int quantity) {
        // lay item trong gio hang ra
       Item item = map.get(id);
        // cap nhat so luong
        item.setQuantity(quantity);
    }
    
    public void empty() {
        map.clear();
    }
    
    public Collection<Item> getItems() {
        return map.values();
    }
    
    public double getTotal() {
        double total = 0;
        for (Item item : map.values()) {
            total += item.getCost();
        }
        return total;
    }
    
    public int getNumOfProducts() {
        return map.size();
    }
    
    private ProductFacade lookupProductFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProductFacade) c.lookup("java:global/new-cart-management/ProductFacade!sessionbeans.ProductFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
