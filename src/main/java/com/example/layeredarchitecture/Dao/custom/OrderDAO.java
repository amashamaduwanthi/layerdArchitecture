package com.example.layeredarchitecture.Dao.custom;

import com.example.layeredarchitecture.Dao.CrudDAO;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderDAO extends CrudDAO<OrderDTO> {
   // String generateNextOrderId() throws SQLException, ClassNotFoundException;
   boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;
    boolean exitOrder(String orderId) throws SQLException, ClassNotFoundException;
}
