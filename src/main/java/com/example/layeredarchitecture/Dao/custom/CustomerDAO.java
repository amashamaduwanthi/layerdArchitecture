package com.example.layeredarchitecture.Dao.custom;

import com.example.layeredarchitecture.Dao.CrudDAO;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerDTO> {
   /*  ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException ;
    boolean saveCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException ;
    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;
     boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException ;
    String generateNextId() throws SQLException, ClassNotFoundException ;

     boolean existCustomer(String id) throws SQLException, ClassNotFoundException ;
     ArrayList<CustomerDTO> loadAllCustomerIDS() throws SQLException, ClassNotFoundException;*/
}
