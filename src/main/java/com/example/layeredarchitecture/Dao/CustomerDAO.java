package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO {
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException ;
    public boolean saveCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException ;
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException ;
    public String generateNextId() throws SQLException, ClassNotFoundException ;

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<CustomerDTO> loadAllCustomerIDS() throws SQLException, ClassNotFoundException;
}
