package lk.ijse.layeredarchitecture.Dao.custom;

import lk.ijse.layeredarchitecture.Dao.CrudDAO;
import lk.ijse.layeredarchitecture.dto.OrderDTO;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderDAO extends CrudDAO<OrderDTO> {
   // String generateNextOrderId() throws SQLException, ClassNotFoundException;
   boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;
    boolean exitOrder(String orderId) throws SQLException, ClassNotFoundException;
}
