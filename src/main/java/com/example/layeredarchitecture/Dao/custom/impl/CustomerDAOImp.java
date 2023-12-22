package com.example.layeredarchitecture.Dao.custom.impl;

import com.example.layeredarchitecture.Dao.custom.CustomerDAO;
import com.example.layeredarchitecture.SqlUtil.Sqlutil;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImp implements CustomerDAO {

@Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
      /*  Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/
        ResultSet rst = Sqlutil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO>getAllCustomerDto=new ArrayList<>();
        while(rst.next()){
          CustomerDTO customerDTO=  new CustomerDTO(rst.getString("id"), rst.getString("name"), rst.getString("address"));
          getAllCustomerDto.add(customerDTO);
        }
        return getAllCustomerDto;
    }

    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return Sqlutil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",
                dto.getId(), dto.getName(), dto.getAddress());
    }


    @Override
   public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
     /*  Connection connection = DBConnection.getDbConnection().getConnection();
       PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
       pstm.setString(1, dto.getAddress());
       pstm.setString(2, dto.getAddress());
       pstm.setString(3, dto.getId());
       return pstm.executeUpdate()>0;*/
        return Sqlutil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",dto.getName(),dto.getAddress(),dto.getId());
   }
    @Override
   public boolean delete(String id) throws SQLException, ClassNotFoundException {
      /* Connection connection = DBConnection.getDbConnection().getConnection();
       PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
       pstm.setString(1, id);
       return  pstm.executeUpdate()>0;*/
        return Sqlutil.execute("DELETE FROM Customer WHERE id=?",id);
   }
    @Override
   public String generateNextId() throws SQLException, ClassNotFoundException {
      /* Connection connection = DBConnection.getDbConnection().getConnection();
       ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");*/
        ResultSet rst=Sqlutil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
       if (rst.next()) {
           String id = rst.getString("id");
           int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
           return String.format("C00-%03d", newCustomerId);
       } else {
           return "C00-001";
       }


   }
    @Override

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();*/
        ResultSet resultSet=Sqlutil.execute("SELECT id FROM Customer WHERE id=?",id);
        return resultSet.next();
    }
    @Override
    public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = Sqlutil.execute("SELECT * FROM Customer WHERE id=?");
        rst.next();
        return new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));
    }

}
