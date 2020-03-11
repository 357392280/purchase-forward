package cn.eight.purchaseforward.dao;

import cn.eight.purchaseforward.pojo.Good;
import cn.eight.purchaseforward.util.DbPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDao {
    private BasicDao dao =new BasicDao();

    public List<String> queryGoodType(){
        String sql="select distinct goodtype from good order by goodtype";
        PreparedStatement pst=null;
        boolean result=false;
        Connection con = DbPool.getConnection();
        ResultSet rs =null;
        try {
            pst=con.prepareStatement(sql);
             rs = dao.execQuery(con, pst, null);
             List<String> goodtypeList=new ArrayList<>();
             while (rs!=null&&rs.next()){


                 goodtypeList.add(rs.getString(1));
             }
             return goodtypeList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResourse(rs,pst,con);
        }
        return null;
    }



    public List<Good> queryGoodByType(String goodtype){
        String sql="select * from good  where goodtype=? order by id limit ?,?";
        PreparedStatement pst=null;
        boolean result=false;
        Connection con = DbPool.getConnection();
        ResultSet rs =null;
        try {
            pst=con.prepareStatement(sql);
            rs = dao.execQuery(con, pst,goodtype,0,15);
            List<Good> goodList=new ArrayList<>();
            while (rs!=null&&rs.next()){
                Good good1=new Good();
                good1.setId(rs.getInt(1));
                good1.setGoodname(rs.getString(2));
                good1.setGoodtype(rs.getString(3));
                good1.setPrice(rs.getDouble(4));
                good1.setPic(rs.getString(5));
                goodList.add(good1);
            }
            return goodList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResourse(rs,pst,con);
        }
        return null;
    }

}
