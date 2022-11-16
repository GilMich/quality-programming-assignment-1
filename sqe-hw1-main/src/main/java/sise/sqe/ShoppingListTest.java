package sise.sqe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.List;

class ShoppingListTest {
    ShoppingList sL_object ;
    private ShoppingList sL_object1;

    @BeforeAll // this shit doesnt work
    static void init_shopping_list()
    {
        Supermarket supermarket = Mockito.mock(Supermarket.class);
        ShoppingList sL_object = new ShoppingList(supermarket) ;
    }

    @org.junit.jupiter.api.Test
    void addProduct_check_if_list_contains_item_success() throws NoSuchFieldException, IllegalAccessException {
        Supermarket supermarket = Mockito.mock(Supermarket.class);
        ShoppingList sL_object = new ShoppingList(supermarket) ;
        Product p1 = new Product("1","bamba",1);
        sL_object.addProduct(p1);
        Field field = sL_object.getClass().getDeclaredField("products");
        field.setAccessible(true);
        List products = (List)field.get(sL_object);
        assertEquals(products.size(),1);
    }

    @Test
    public void addProductSuccessTest() {

    }

    @org.junit.jupiter.api.Test
    void getMarketPrice() {
    }

    @org.junit.jupiter.api.Test
    void getDiscount() {
    }

    @org.junit.jupiter.api.Test
    void priceWithDelivery() {
    }

    @org.junit.jupiter.api.Test
    void changeQuantity() {
    }
}