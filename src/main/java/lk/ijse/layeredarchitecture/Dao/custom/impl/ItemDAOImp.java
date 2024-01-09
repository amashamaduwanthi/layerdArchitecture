package lk.ijse.layeredarchitecture.Dao.custom.impl;

import lk.ijse.layeredarchitecture.Dao.custom.ItemDAO;
import lk.ijse.layeredarchitecture.SqlUtil.Sqlutil;
import lk.ijse.layeredarchitecture.dto.ItemDTO;
import lk.ijse.layeredarchitecture.entity.Item;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImp implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
       // Connection connection = DBConnection.getDbConnection().getConnection();
       // Statement stm = connection.createStatement();
        ResultSet rst = Sqlutil.execute("SELECT * FROM Item");
        ArrayList<Item> getAllItem=new ArrayList<>();
        while(rst.next()){
          Item item=  new Item(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
          getAllItem.add(item);
        }
        return getAllItem;
    }
    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
       // Connection connection = DBConnection.getDbConnection().getConnection();
       // PreparedStatement pstm = connection.prepareStatement();
       // pstm.setString(1, code);
      //  return pstm.executeUpdate()>0;
        return Sqlutil.execute("DELETE FROM Item WHERE code=?",code);
    }
    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
    /*    Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1, itemDTO.getCode());
        pstm.setString(2, itemDTO.getDescription());
        pstm.setBigDecimal(3, itemDTO.getUnitPrice());
        pstm.setInt(4, itemDTO.getQtyOnHand());
        return pstm.executeUpdate()>0;*/
        return Sqlutil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }
    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
     /*   Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, itemDTO.getDescription());
        pstm.setBigDecimal(2, itemDTO.getUnitPrice());
        pstm.setInt(3, itemDTO.getQtyOnHand());
        pstm.setString(4, itemDTO.getCode());
       return pstm.executeUpdate()>0;*/
        return Sqlutil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getCode());
    }
    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
      /*  Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");*/
        ResultSet rst=Sqlutil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
      /*  Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();*/
      ResultSet resultSet= Sqlutil.execute("SELECT code FROM Item WHERE code=?", code);
      return resultSet.next();

    }



    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public ItemDTO FindItem(String newcode) throws SQLException, ClassNotFoundException {
      // Connection connection = DBConnection.getDbConnection().getConnection();
     //  PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
      //  pstm.setString(1, newcode);
        ResultSet rst = Sqlutil.execute("SELECT * FROM Item WHERE code=?",newcode);
        rst.next();
       ItemDTO item= new ItemDTO(newcode, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        return item;
    }
}
