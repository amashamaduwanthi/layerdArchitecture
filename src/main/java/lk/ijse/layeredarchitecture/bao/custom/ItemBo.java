package lk.ijse.layeredarchitecture.bao.custom;

import lk.ijse.layeredarchitecture.Dao.SuperDAO;
import lk.ijse.layeredarchitecture.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo extends SuperDAO {
    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException ;
    boolean existItem(String id) throws SQLException, ClassNotFoundException ;
    boolean deleteItem(String id) throws SQLException, ClassNotFoundException ;
    boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException ;
    boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException ;
    String generateNextId() throws SQLException, ClassNotFoundException ;
}
