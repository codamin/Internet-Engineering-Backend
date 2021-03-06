package Loghme.services;

import Loghme.controllers.DTOs.OrderDTO;
import Loghme.database.dataMappers.order.OrderMapper;
import Loghme.entities.Order;
import Loghme.scheduler.HandleDeliveriesPeriodic;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class CartService {
    
    public static OrderDTO getCart(String email) {
        OrderMapper orderMapper = OrderMapper.getInstance();
        Order resp = new Order();
        try {
            resp = orderMapper.getCart(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        OrderDTO respDTO = new OrderDTO(resp);
        return respDTO;
    }

    public static void addToCart(String userId, String restaurantId, String foodName, int number) throws SQLException {
        OrderMapper orderMapper = OrderMapper.getInstance();
        orderMapper.addToCart(userId, restaurantId, foodName, number);
    }

    public static void deleteFromCart(String userId, String restaurantId, String foodName) throws SQLException {
        OrderMapper orderMapper = OrderMapper.getInstance();
        orderMapper.addToCart(userId, restaurantId, foodName, -1);
    }

    public static void finalizeCart(String userId) throws SQLException {
        OrderMapper orderMapper = OrderMapper.getInstance();
        Order order = orderMapper.finalizeCart(userId);
        if(order == null)
            return;
        TimerTask getDataPeriodic = new HandleDeliveriesPeriodic(order);
        new Timer().schedule(getDataPeriodic, 0, 3 * 1000);
    }
}
