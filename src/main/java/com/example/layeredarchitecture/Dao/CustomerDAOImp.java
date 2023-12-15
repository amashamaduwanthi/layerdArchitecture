package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImp {
    public CustomerDAOImp() throws SQLException, ClassNotFoundException {
    }

    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
        ArrayList<CustomerDTO>getAllCustomerDto=new ArrayList<>();
        while(rst.next()){
          CustomerDTO customerDTO=  new CustomerDTO(rst.getString("id"), rst.getString("name"), rst.getString("address"));
          getAllCustomerDto.add(customerDTO);
        }
        return getAllCustomerDto;
    }
   public boolean saveCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException {
       Connection connection = DBConnection.getDbConnection().getConnection();
       PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
       pstm.setString(1, id);
       pstm.setString(2, name);
       pstm.setString(3, address);
       return pstm.executeUpdate()>0;

   }
   public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
       Connection connection = DBConnection.getDbConnection().getConnection();
       PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
       pstm.setString(1, dto.getAddress());
       pstm.setString(2, dto.getAddress());
       pstm.setString(3, dto.getId());
       return pstm.executeUpdate()>0;
   }
   public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
       Connection connection = DBConnection.getDbConnection().getConnection();
       PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
       pstm.setString(1, id);
       return  pstm.executeUpdate()>0;
   }
   public String generateNextId() throws SQLException, ClassNotFoundException {
       Connection connection = DBConnection.getDbConnection().getConnection();
       ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
       if (rst.next()) {
           String id = rst.getString("id");
           int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
           return String.format("C00-%03d", newCustomerId);
       } else {
           return "C00-001";
       }


   }


    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();
    }
}
