package lk.ijse.layeredarchitecture.bao.custom;

import lk.ijse.layeredarchitecture.Dao.SuperDAO;
import lk.ijse.layeredarchitecture.dto.CustomerDTO;
import lk.ijse.layeredarchitecture.dto.ItemDTO;
import lk.ijse.layeredarchitecture.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBo extends SuperDAO {
     boolean PlaceOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
      ItemDTO findItem(String code);
    // CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;
    String generateNextId() throws SQLException, ClassNotFoundException ;
    ArrayList<CustomerDTO> getAllCustomerId() throws SQLException, ClassNotFoundException ;
    ArrayList<ItemDTO> getAllItemrId() throws SQLException, ClassNotFoundException ;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException ;
    boolean existItem(String id) throws SQLException, ClassNotFoundException ;
}
