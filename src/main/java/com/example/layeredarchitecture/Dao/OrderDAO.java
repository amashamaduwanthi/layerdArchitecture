package com.example.layeredarchitecture.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderDAO {
     String generateNextOrderId() throws SQLException, ClassNotFoundException;
    boolean saveOrder(String orderId, LocalDate orderDate, String customerId, Connection connection) throws SQLException;
    boolean exitOrder(String orderId, Connection connection) throws SQLException;
}
