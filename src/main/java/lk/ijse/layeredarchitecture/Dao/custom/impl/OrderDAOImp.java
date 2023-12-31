package lk.ijse.layeredarchitecture.Dao.custom.impl;

import lk.ijse.layeredarchitecture.Dao.custom.OrderDAO;
import lk.ijse.layeredarchitecture.SqlUtil.Sqlutil;
import lk.ijse.layeredarchitecture.dto.OrderDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDAOImp implements OrderDAO {
    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
     /*   Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");*/
        ResultSet rst = Sqlutil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");

        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {
      /*  PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, orderId);
        stm.setDate(2, Date.valueOf(orderDate));
        stm.setString(3, customerId);

        if (stm.executeUpdate() != 1) {
          //  connection.rollback();
           // connection.setAutoCommit(true);
            return false;
        }else{
            return true;
        }*/
        return Sqlutil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)", orderId, orderDate, customerId);
    }

    @Override
    public boolean exitOrder(String orderId) throws SQLException, ClassNotFoundException {

      /*  PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        stm.setString(1, orderId);
        if(stm.executeQuery().next()){
           // connection.setAutoCommit(false);
            return true;
        }else{
           // connection.setAutoCommit(false);
            return false;
        }

    }*/
       ResultSet resultSet= Sqlutil.execute("SELECT oid FROM `Orders` WHERE oid=?", orderId);
       return resultSet.next();
    }

    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }



    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public OrderDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}