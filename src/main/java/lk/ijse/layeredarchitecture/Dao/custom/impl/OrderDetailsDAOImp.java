package lk.ijse.layeredarchitecture.Dao.custom.impl;

import lk.ijse.layeredarchitecture.Dao.custom.OrderDetailDAO;
import lk.ijse.layeredarchitecture.SqlUtil.Sqlutil;
import lk.ijse.layeredarchitecture.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImp implements OrderDetailDAO {
    @Override
    public boolean save(String orderId, OrderDetailDTO orderDetails) throws SQLException, ClassNotFoundException {
     /*   PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");

        for (OrderDetailDTO detail : orderDetails) {
            stm.setString(1, orderId);
            stm.setString(2, detail.getItemCode());
            stm.setBigDecimal(3, detail.getUnitPrice());
            stm.setInt(4, detail.getQty());
        }
        if (stm.executeUpdate() != 1) {
            //   connection.rollback();
            //  connection.setAutoCommit(true);
            return false;
        }else{
            return true;
        }*/
        return Sqlutil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",orderId , orderDetails.getItemCode(), orderDetails.getUnitPrice(), orderDetails.getQty());


    }




    @Override
    public ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetailDTO search(String id) throws SQLException, ClassNotFoundException {
      return null;
    }
}



