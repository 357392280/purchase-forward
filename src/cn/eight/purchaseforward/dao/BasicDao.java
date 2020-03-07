package cn.eight.purchaseforward.dao;

import cn.eight.purchaseforward.util.DbPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//项目中对数据库两大操作，一个时查询，一个时增删改
public class BasicDao {
    //公共的查询
    public ResultSet execQuery(Connection con, PreparedStatement pst,Object...params){
        ResultSet rs=null;
        //针对传参
        try {
            if (params!=null){
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i+1,params[i]);
                }
            }

             rs = pst.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }
    //公共的增删改
    public void execUpdate(Connection con, PreparedStatement pst,Object...params) throws SQLException {
        //针对传参
        if (params !=null){
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i+1,params[i]);
            }
        }
            pst.executeUpdate();

    }
    public void  releaseResourse(ResultSet rs,PreparedStatement pst,Connection con){
        try {
            if (rs !=null){
                rs.close();
            }
            if (pst!=null){
                pst.close();

            }
            if (con!=null){
                con.close();
                con=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection con= DbPool.getConnection();
        String sql="insert user(username,password,rule,email,qq) value (?,?,?,?,?)";
        BasicDao dao=new BasicDao();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            dao.execUpdate(con,pst,"admin","123456",0,null,null);
            dao.releaseResourse(null,pst,con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
