package lk.ijse.layeredarchitecture.Dao.custom;

import lk.ijse.layeredarchitecture.Dao.CrudDAO;
import lk.ijse.layeredarchitecture.dto.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetailDTO> {
     boolean save(String orderId, OrderDetailDTO orderDetails ) throws SQLException, ClassNotFoundException;

}
