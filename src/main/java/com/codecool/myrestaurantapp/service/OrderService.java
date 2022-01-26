package com.codecool.myrestaurantapp.service;

import com.codecool.myrestaurantapp.model.Customer;
import com.codecool.myrestaurantapp.model.Order;
import com.codecool.myrestaurantapp.model.Receipt;
import com.codecool.myrestaurantapp.service.dao.CustomerDaoMem;
import com.codecool.myrestaurantapp.service.dao.OrderDaoMem;
import com.codecool.myrestaurantapp.service.dao.ReceiptDaoMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    OrderDaoMem orderDaoMem;
    ReceiptDaoMem receiptDaoMem;
    CustomerDaoMem customerDaoMem;

    @Autowired
    public OrderService(OrderDaoMem orderDaoMem, ReceiptDaoMem receiptDaoMem, CustomerDaoMem customerDaoMem) {
        this.orderDaoMem = orderDaoMem;
        this.receiptDaoMem = receiptDaoMem;
        this.customerDaoMem = customerDaoMem;
    }

    public Set<Order> getActiveOrders() {
         return orderDaoMem.getActiveOrders();
    }

    public Set<Order> getFulfilledOrders() {
        return orderDaoMem.getFulfilledOrders();
    }

    public Order addNewOrder(String [] foods, String customerName) {
        List<Receipt> orderElements = new ArrayList<>();
        for (String food : foods) {
            for (Receipt receipt : receiptDaoMem.getAllReceipt()) {
                if (food.equals(receipt.getName())) {
                    orderElements.add(receipt);
                }
            }
        }
        Customer customer = customerDaoMem.findCustomer(customerName);
        Order newOrder = Order.builder()
                .foods(orderElements)
                .id(orderDaoMem.getStoredOrdersNumber()+1)
                .orderTime(LocalDateTime.now())
                .customer(customer)
                .build();
        orderDaoMem.addOrder(newOrder);
        return newOrder;
    }
}