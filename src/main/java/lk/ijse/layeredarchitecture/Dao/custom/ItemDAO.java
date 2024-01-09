package lk.ijse.layeredarchitecture.Dao.custom;

import lk.ijse.layeredarchitecture.Dao.CrudDAO;
import lk.ijse.layeredarchitecture.dto.ItemDTO;
import lk.ijse.layeredarchitecture.entity.Item;

import java.sql.*;

public interface ItemDAO extends CrudDAO<Item> {

    public ItemDTO FindItem(String newcode) throws SQLException, ClassNotFoundException;
   /*  ArrayList<ItemDTO> loadAllItem() throws SQLException, ClassNotFoundException ;
     boolean deleteItem(String code) throws SQLException, ClassNotFoundException ;
    boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;
     String generateNextId() throws SQLException, ClassNotFoundException ;
     boolean existItem(String code) throws SQLException, ClassNotFoundException ;
     ArrayList<ItemDTO> loadAllItemIDS() throws SQLException, ClassNotFoundException;
    boolean updateitem(ItemDTO item, Connection connection) throws SQLException, ClassNotFoundException;
    ItemDTO FindItem(String newcode) throws SQLException, ClassNotFoundException;*/
}
