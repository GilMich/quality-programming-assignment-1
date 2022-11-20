import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import sise.sqe.Product;
import sise.sqe.ShoppingList;
import sise.sqe.Supermarket;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


class ShoppingListTest {
    static Supermarket supermarket;
    static ShoppingList sL_object;

    @BeforeEach
    public void init_shopping_list()
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
        //products.remove(p1);
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
    @ParameterizedTest
    @ValueSource(ints = {1001, 2000, 3000, 4000, 5000})
    void getDiscount_TestIfPriceAbove1000Returns085(int number) {
       assertEquals(sL_object.getDiscount(number), 0.85); }
    @ParameterizedTest
    @ValueSource(ints = {751,850,950,1000})
    void getDiscount_TestIfPriceBetween750and1000Returns09(int number) {
        assertEquals(sL_object.getDiscount(number), 0.9);
    }
    @ParameterizedTest
    @ValueSource(ints = {501,600,700,750})
    void getDiscount_TestIfPriceBetween500and750Returns095(int number) {
        assertEquals(sL_object.getDiscount(number), 0.95);
    }

    @ParameterizedTest
    @ValueSource(ints = {500,400,300,100,0})
    void getDiscount_TestIfPriceSmallerThan500Returns1(int number) {
        assertEquals(sL_object.getDiscount(number), 1);
    }



    @Test
    public void priceWithDelivery_TestIfNegativeMilesThrowsException() throws IllegalArgumentException {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {sL_object.priceWithDelivery(-20);});
        assertEquals(exception.getMessage(), "Miles cannot be negative");
    }


@Test
    public void priceWithDelivery_TestWithZeroProductsReturnZero()
    {
        ShoppingList shoppingListSpy = Mockito.spy(new ShoppingList(supermarket));
    assertEquals(shoppingListSpy.priceWithDelivery(0),0);
    }

    @Test
    public void priceWithDelivery_TestCorrectPriceSuccess() throws NoSuchFieldException {
        ShoppingList shoppingListSpy = Mockito.spy(new ShoppingList(supermarket));
        Product p1 = new Product("1","bamba",1);
        Product p2 = new Product("1","bisli",1);
        shoppingListSpy.addProduct(p1);
        shoppingListSpy.addProduct(p2);
        Mockito.verify(shoppingListSpy).addProduct(p1);
        Mockito.verify(shoppingListSpy).addProduct(p2);
        when(supermarket.getPrice(anyString())).thenReturn(1.0);
        when(shoppingListSpy.getMarketPrice()).thenReturn(2.0);
        when(supermarket.calcDeliveryFee(anyInt(),anyInt())).thenReturn(5.0);
        assertEquals(7, shoppingListSpy.priceWithDelivery(5));

    }

    @org.junit.jupiter.api.Test
    void changeQuantity_TestIfNegativeQuantityThrowsException()throws IllegalArgumentException {
        /**  need to implement here! I think a spy would be good here! */
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {sL_object.changeQuantity(-1, "milk");});
        assertEquals(exception.getMessage(), "Quantity cannot be negative");

    }


    @Test
    void changeQuantity_TestZeroQuantitySuccess() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {

        Product p1 = new Product("1","bamba",1);
        sL_object.addProduct(p1);
        sL_object.changeQuantity(0,"1");
        Field field = sL_object.getClass().getDeclaredField("products");
        field.setAccessible(true);
        List products = (List)field.get(sL_object);

        assertEquals(false,products.contains(p1));
        assertEquals(0,products.size());




        //assertEquals(2, spyList.size());
        //spyList.add(p2);


        //Mockito.verify(spyList).add(p2);
        //sL_object.addProduct(p1);





    }
    @Test
    void changeQuantity_TestPositiveQuantitySuccess() throws IllegalArgumentException {
        ShoppingList shoppingListSpy = Mockito.spy(new ShoppingList(supermarket));
        Product p1 = new Product("1","bamba",3);
        shoppingListSpy.addProduct(p1);
        Mockito.verify(shoppingListSpy).addProduct(p1);
        shoppingListSpy.changeQuantity(1,"1");
        Mockito.verify(shoppingListSpy).changeQuantity(1,"1");
        assertEquals(1,p1.getQuantity());
    }

















}