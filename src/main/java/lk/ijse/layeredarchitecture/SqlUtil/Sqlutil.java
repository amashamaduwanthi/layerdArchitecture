package lk.ijse.layeredarchitecture.SqlUtil;

import lk.ijse.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sqlutil {
    public static<T> T execute(String sql,Object...ob) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm=connection.prepareStatement(sql);
        for(int i=0;i<ob.length;i++){
            pstm.setObject((i+1),ob[i]);

        }
        if(sql.startsWith("SELECT")){
            return(T) pstm.executeQuery();
        }else{
            return(T)((Boolean)(pstm.executeUpdate()>0));
        }
    }
}
