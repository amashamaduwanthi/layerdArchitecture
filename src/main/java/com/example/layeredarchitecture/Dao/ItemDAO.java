package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {
     ArrayList<ItemDTO> loadAllItem() throws SQLException, ClassNotFoundException ;
     boolean deleteItem(String code) throws SQLException, ClassNotFoundException ;
    boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;
     String generateNextId() throws SQLException, ClassNotFoundException ;
     boolean existItem(String code) throws SQLException, ClassNotFoundException ;
     ArrayList<ItemDTO> loadAllItemIDS() throws SQLException, ClassNotFoundException;
    boolean updateitem(ItemDTO item, Connection connection) throws SQLException;
    ItemDTO FindItem(String newcode) throws SQLException, ClassNotFoundException;
}
