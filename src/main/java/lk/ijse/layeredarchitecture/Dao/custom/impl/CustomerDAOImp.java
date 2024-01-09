package lk.ijse.layeredarchitecture.Dao.custom.impl;

import lk.ijse.layeredarchitecture.Dao.custom.CustomerDAO;
import lk.ijse.layeredarchitecture.SqlUtil.Sqlutil;
import lk.ijse.layeredarchitecture.entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImp implements CustomerDAO {

@Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
      /*  Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/
        ResultSet rst = Sqlutil.execute("SELECT * FROM Customer");
        ArrayList<Customer>getAllCustomerDto=new ArrayList<>();
        while(rst.next()){
          Customer entity=  new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"));
          getAllCustomerDto.add(entity);
        }
        return getAllCustomerDto;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return Sqlutil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",
                entity.getId(), entity.getName(), entity.getAddress());
    }


    @Override
   public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
     /*  Connection connection = DBConnection.getDbConnection().getConnection();
       PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
       pstm.setString(1, dto.getAddress());
       pstm.setString(2, dto.getAddress());
       pstm.setString(3, dto.getId());
       return pstm.executeUpdate()>0;*/
        return Sqlutil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",entity.getName(),entity.getAddress(),entity.getId());
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
    public Customer search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = Sqlutil.execute("SELECT * FROM Customer WHERE id=?");
        rst.next();
        return new Customer(newValue + "", rst.getString("name"), rst.getString("address"));
    }

}
