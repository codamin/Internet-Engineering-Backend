package Loghme.entities;

import Loghme.exceptions.ForbiddenException;
import java.util.ArrayList;

public class Cart {

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    private String restaurantId;
    private String restaurantName;
    private ArrayList<OrderItem> orderItems;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Cart() {
        this.orderItems = new ArrayList<OrderItem>();
        restaurantId = null;
        restaurantName = null;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public boolean has(Food food) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getFood().getName().equals(food.getName())) {
                return true;
            }
        }
        return false;
    }

    private OrderItem findOrderItemByFood(Food food) {
        for(OrderItem item: orderItems)
            if(item.getFood().getName().equals(food.getName()))
                return item;
        return null;
    }

    public OrderItem getOrderItem(String restaurantId, String foodName) {
        for(OrderItem item: orderItems)
            if(item.getFood().getRestaurantId().equals(restaurantId) && item.getFood().getName().equals(foodName))
                return item;
        OrderItem respItem = new OrderItem(null);
        respItem.setNumber(0);
        return respItem ;
    }

    public void incrementOrder(Food food) {
        OrderItem orderItem = findOrderItemByFood(food);
        orderItem.setNumber(orderItem.getNumber()+1);
    }

    public void decrementOrder(Food food) {
        OrderItem orderItem = findOrderItemByFood(food);
        orderItem.setNumber(orderItem.getNumber()-1);
        if(orderItem.getNumber() <= 0) {
            orderItems.remove(orderItem);
        }
    }

    public void clear() {
        orderItems.clear();
        restaurantId = null;
    }

    private boolean checkFoodRestaurant(String restaurantId) {
        for(OrderItem orderItem : orderItems)
            if (!(orderItem.getFood().getRestaurantId().equals(restaurantId)))
                return false;
        return true;
    }

    public void addFood(Food food) {

        if(!checkFoodRestaurant(food.getRestaurantId()))
            throw new ForbiddenException("Restaurant of the food is not similar to the others");

        if(!food.isAvailable())
            throw new ForbiddenException("the food has been finished");

        if(has(food)) {
            this.incrementOrder(food);
            food.decrement();
        }
        else {
            if(restaurantId == null) {
                this.restaurantId = food.getRestaurantId();
                this.restaurantName = food.getRestaurantName();
            }
            OrderItem newOrderItem = new OrderItem(food);
            orderItems.add(newOrderItem);
            food.decrement();
        }
        return;
    }

    public void deleteFood(Food food) {
        if(!checkFoodRestaurant(food.getRestaurantId()))
            throw new ForbiddenException("The food is not in cart to be removed");

        if(has(food)) {
            this.decrementOrder(food);
            food.increment();
        }
    }

    public int getFinalPrice() {
        int price = 0;

        for(OrderItem orderItem : orderItems)
            price += orderItem.getPrice();

        return price;
    }

    public boolean isEmpty() {
        return (orderItems.size() == 0);
    }
}