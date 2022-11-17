package sise.sqe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ShoppingListTest {
    static Supermarket supermarket;
    static ShoppingList sL_object;

    @BeforeAll
    public static void init_shopping_list()
    {
        supermarket = Mockito.mock(Supermarket.class);
        sL_object = new ShoppingList(supermarket) ;
    }

    @org.junit.jupiter.api.Test
    void addProduct_check_if_list_contains_item_success() throws NoSuchFieldException, IllegalAccessException {
        Product p1 = new Product("1","bamba",1);
        sL_object.addProduct(p1);
        Field field = sL_object.getClass().getDeclaredField("products");
        field.setAccessible(true);
        List products = (List)field.get(sL_object);
        assertEquals(products.size(),1);
    }

//    }
//    public double getMarketPrice() {
//        double price = 0.0;
//
//        // Calculates the sum of all prices
//        for (Product product : products) {
//            price += supermarket.getPrice(product.productId) * product.getQuantity();
//        }
//        // Calculates the price after discount
////        return price * getDiscount(price);
//    }
    @org.junit.jupiter.api.Test
    void getMarketPriceTestIfSumIsCorrectSuccess() {
        Product p1 = new Product("1","bamba",2);
        Product p2 = new Product("1","bisli",2);
        when(supermarket.getPrice(anyString())).thenReturn(2.0);
        sL_object.addProduct(p1);
        sL_object.addProduct(p2);
        assertEquals(8, sL_object.getMarketPrice());

    }
//    public double getDiscount(double price) {
//        // Negative price
//        if(price < 0)
//            throw new IllegalArgumentException("Price cannot be negative");
//
//        // 1000 < price
//        if (price > 1000)
//            return 0.85;
//
//            // 750 < price <= 1000
//        else if (price > 750)
//            return 0.9;
//
//            // 500 < price <= 750
//        else if (price > 500)
//            return 0.95;
//
//            // price < 500 -> no discount!
//        else
//            return 1;
//    }

    @org.junit.jupiter.api.Test
    void getDiscount_TestIfNegativePriceThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {sL_object.getDiscount(-5);});
        assertEquals(exception.getMessage(), "Price cannot be negative");
    }
    @org.junit.jupiter.api.Test
    void getDiscount_TestIfPriceAbove1000Returns085() {
       assertEquals(sL_object.getDiscount(1005), 0.85);
    }
    @org.junit.jupiter.api.Test
    void getDiscount_TestIfPriceBetween750and1000Returns09() {
        assertEquals(sL_object.getDiscount(800), 0.9);
    }
    @org.junit.jupiter.api.Test
    void getDiscount_TestIfPriceBetween500and750Returns095() {
        assertEquals(sL_object.getDiscount(600), 0.95);
    }

    @org.junit.jupiter.api.Test
    void getDiscount_TestIfPriceSmallerThan500Returns1() {
        assertEquals(sL_object.getDiscount(400), 1);
        assertEquals(sL_object.getDiscount(0), 1);
    }
    @Test
    public void priceWithDeliveryTest(int miles) throws IllegalArgumentException {



        // Negative miles
//        if (miles < 0)
//            throw new IllegalArgumentException("Miles cannot be negative");
//
//        // calculates delivery fee
//        int numOfProducts = products.size();
//        double deliveryFee = supermarket.calcDeliveryFee(miles, numOfProducts);
//        // calculates price
//        double price = getMarketPrice();
//        // total price = deliveryFee + price
//        return price + deliveryFee;
    }


    @org.junit.jupiter.api.Test
    void changeQuantity() {
        /**  need to implement here! I think a spy would be good here! */
    }
}