package lk.ijse.layeredarchitecture.bao.custom.impl;

import lk.ijse.layeredarchitecture.Dao.DAOFactory;
import lk.ijse.layeredarchitecture.Dao.custom.ItemDAO;
import lk.ijse.layeredarchitecture.bao.custom.ItemBo;
import lk.ijse.layeredarchitecture.dto.ItemDTO;
import lk.ijse.layeredarchitecture.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {
    ItemDAO itemDAOImp= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
      ArrayList<ItemDTO>itemDTOS=new ArrayList<>();
      ArrayList<Item>items=itemDAOImp.getAll();
      for(Item item:items){
          itemDTOS.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
      }
      return itemDTOS;
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAOImp.exist(id);
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAOImp.delete(id);
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
      return itemDAOImp.save(new Item(dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAOImp.update(new Item(dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand()));
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
       return itemDAOImp.generateNextId();
    }
}
