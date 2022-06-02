/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import entities.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.ProductFacade;

/**
 *
 * @author SE151470 Tran Vuong Anh Tuan
 */
public class Cart_List {
    
    ProductFacade pf = lookupProductFacadeBean();
    List<Item> list = null;
    
    public Cart_List() {
        list = new ArrayList<>();
    }
    
    public void add(int id, int quantity) {
        //tim item trong cart
        Item item = find(id);
        //neu item chua co trong cart thi tao moi item va them vao cart
        if (item == null) {
            Product product = pf.find(id);
            item = new Item(product.getId(), product.getDescription(), product.getPrice(), product.getDiscount(), quantity);
            list.add(item);
        } else { // neu da co item trong cart thi cap nhat quantity
            item.setQuantity(item.getQuantity() + quantity);
        }
    }
    
    public Item find(int id) {
        for (Item item : list) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    
    public void delete(int id) {
        Item item = find(id);
        list.remove(item);
    }
    
    public void update(int id, int quantity) {
        // lay item trong gio hang ra
        Item item = find(id);
        // cap nhat so luong
        item.setQuantity(quantity);
    }
    
    public void empty() {
        list.clear();
    }
    
    public List<Item> getItems() {
        return list;
    }
    
    public double getTotal() {
        double total = 0;
        for (Item item : list) {
            total += item.getCost();
        }
        return total;
    }
    
    public int getNumOfProducts() {
        return list.size();
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
