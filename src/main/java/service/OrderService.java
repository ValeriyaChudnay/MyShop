package service;

import db.TransactionManager;
import entity.Order;
import entity.ShoesInfo;
import org.apache.log4j.Logger;
import repository.OrderRepository;

public class OrderService {
    private static final Logger logger = Logger.getLogger(ProductService.class);
    private OrderRepository repo;
    private TransactionManager transactionManager;

    public OrderService(OrderRepository repo, TransactionManager transactionManager) {
        this.repo = repo;
        this.transactionManager = transactionManager;
    }
    public int makeOrder(Order order){
        int id= transactionManager.doSQL(()->repo.add(order));
        return id;
    }
}
