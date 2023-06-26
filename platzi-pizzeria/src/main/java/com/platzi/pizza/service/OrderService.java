package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.proyection.OrderSumary;
import com.platzi.pizza.persistence.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private static final String Delivery = "D";
    private static final String Carryout = "C";
    private static final String Onsite = "S";

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        return this.orderRepository.findAll();
    }

    public List<OrderEntity> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.orderRepository.findAllByDateBefore(today);
    }

    public List<OrderEntity> getOutsideOrders(){
        List<String> methods = Arrays.asList(Delivery, Carryout);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> findCustomerOrders(String idCustomer){
        return this.orderRepository.findCustomerOrders(idCustomer);
    }

    public OrderSumary getSumary(int orderId){
        return this.orderRepository.findSummary(orderId);
    }
}
