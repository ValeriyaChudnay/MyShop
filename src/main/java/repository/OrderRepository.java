package repository;

import entity.Order;
import entity.ShoesInfo;
import entity.product.Shoes;

import java.sql.SQLException;

public interface OrderRepository {
   <S extends Shoes> boolean save(S product) throws SQLException;

  int add(Order order) throws SQLException;
    boolean addProduct(Order order, int orderId) throws SQLException;
}
