package com.example.layeredarchitecture.bao;

import com.example.layeredarchitecture.Dao.SuperDAO;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBo extends SuperDAO {
     boolean PlaceOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
      ItemDTO findItem(String code);
     CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;
    String generateNextId() throws SQLException, ClassNotFoundException ;
    ArrayList<CustomerDTO> getAllCustomerId() throws SQLException, ClassNotFoundException ;
    ArrayList<ItemDTO> getAllItemrId() throws SQLException, ClassNotFoundException ;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException ;
    boolean existItem(String id) throws SQLException, ClassNotFoundException ;
}
