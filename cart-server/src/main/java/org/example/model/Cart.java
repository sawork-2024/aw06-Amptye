package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import com.micropos.dto.UserDto;
import com.micropos.dto.ProductDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();

    public Cart(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean addItem(Item item) {
        return items.add(item);
    }

    @Override
    public String toString() {
        if (items.isEmpty()){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(i+1).append("\t").append(items.get(i).toString()).append("\n");
            total += items.get(i).getQuantity() * items.get(i).getProductPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }

    public boolean combineItems(int a, int b){
        if((a >= 0) && (a < items.size()) && (b >= 0) && (b < items.size())){
            if(items.get(a).getProductId() == (items.get(b).getProductId())){
                items.get(a).combineWith(items.get(b));
                items.remove(b);
                return true;
            }
        }
        return false;
    }

    public boolean combineIdItems(long productId){
        boolean res = false;
        List<Item> newitems = new ArrayList<>();
        boolean repeat = false;
        for (Item item : items) {
            repeat = false;
            if(productId == (item.getProductId())) {
                res = true;
                for (Item newitem : newitems) {
                    if (productId == (newitem.getProductId())) {
                        newitem.combineWith(item);
                        repeat = true;
                    }
                }
            }
            if(!repeat){
                newitems.add(item);
            }
        }
        this.items = newitems;
        return res;
    }

    public void combineAllItems(){
        List<Item> newitems = new ArrayList<>();
        boolean repeat = false;
        for (Item item : items) {
            repeat = false;
            for (Item newitem : newitems) {
                if (item.getProductId() == (newitem.getProductId())) {
                    newitem.combineWith(item);
                    repeat = true;
                }
            }
            if(!repeat){
                newitems.add(item);
            }
        }
        this.items = newitems;
    }

    public boolean removeItem(int index){
        boolean res = false;
        if((index >= 0) && (index < items.size())){
            res = true;
            items.remove(index);
        }
        return res;
    }

    public boolean removeIdItems(long productId){
        boolean res = false;
        List<Item> newitems = new ArrayList<>();
        for (Item item : items) {
            if(productId == (item.getProductId())) {
                res = true;
            }
            else{
                newitems.add(item);
            }
        }
        this.items = newitems;
        return res;
    }

    public void clearItems(){
        this.items.clear();
    }

    public void addItemQuanity(int index, int quantity){
        this.items.get(index).addQuanity(quantity);
    }
    public void subItemQuanity(int index, int quantity){
        this.items.get(index).subQuanity(quantity);
        if (this.items.get(index).getQuantity() <= 0){
            this.items.remove(index);
        }
    }


}
