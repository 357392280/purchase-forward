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
    public boolean insertGood(Good good){
        String sql="insert into good(goodname,goodtype,price,pic) values(?,?,?,?)";
        PreparedStatement pst=null;
        boolean result=false;
        Connection con = DbPool.getConnection();
        try {
            con.setAutoCommit(false);
            pst = con.prepareStatement(sql);
            dao.execUpdate(con,pst,good.getGoodname(),good.getGoodtype(),good.getPrice(),good.getPic());
            result=true;
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            dao.releaseResourse(null,pst,con);
        }
        return result;

    }
    public List<Good> queryGoodCritania(Good good){
        String sql="select id,goodname,goodtype,price from good";
        String criternia="";
        PreparedStatement pst=null;
        boolean result=false;
        Connection con = DbPool.getConnection();
        ResultSet rs =null;

        if (good.getId()!=-1){
            criternia="id="+good.getId();
        }
        if (!good.getGoodname().trim().isEmpty()){
            if (criternia.isEmpty()){
                criternia+=" goodname like '%"+good.getGoodname()+"%'";
            }else {
                criternia+=" and goodname like '%"+good.getGoodname()+"%'";
            }
        }
        if (!good.getGoodtype().trim().isEmpty()){
            if (criternia.isEmpty()){
                criternia+=" goodtype like '%"+good.getGoodtype()+"%'";
            }else {
                criternia+=" and goodtype like '%"+good.getGoodtype()+"%'";
            }
        }
        //
        if (!criternia.isEmpty()){
            sql+=" where "+criternia;
        }
        try {
            pst=con.prepareStatement(sql);
             rs = dao.execQuery(con, pst, null);
             List<Good> goodList=new ArrayList<>();
             while (rs!=null&&rs.next()){
                 Good good1=new Good();
                 good1.setId(rs.getInt(1));
                 good1.setGoodname(rs.getString(2));
                 good1.setGoodtype(rs.getString(3));
                 good1.setPrice(rs.getDouble(4));
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

    public List<Good> queryGoodAll(int pageNow,int pageSize){
        String sql="select * from good order by id limit ?,?";
        PreparedStatement pst=null;
        boolean result=false;
        Connection con = DbPool.getConnection();
        ResultSet rs =null;
        try {
            pst=con.prepareStatement(sql);
            rs = dao.execQuery(con, pst,(pageNow-1)*pageSize,pageSize);
            List<Good> goodList=new ArrayList<>();
            while (rs!=null&&rs.next()){
                Good good1=new Good();
                good1.setId(rs.getInt(1));
                good1.setGoodname(rs.getString(2));
                good1.setGoodtype(rs.getString(3));
                good1.setPrice(rs.getDouble(4));
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


    public int queryTotalRecond(){
        int result=0;
        String sql="select count(*) from good";
        PreparedStatement pst=null;
        Connection con = DbPool.getConnection();
        ResultSet rs =null;
        try {
            pst=con.prepareStatement(sql);
            rs = dao.execQuery(con, pst,null);
            List<Good> goodList=new ArrayList<>();
            if (rs!=null&&rs.next()){
                result=rs.getInt(1);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResourse(rs,pst,con);
        }
        return 0;
    }


    public boolean modGood(Good good){
        String sql="update good set goodname=?,goodtype=?,price=? where id=?";
        PreparedStatement pst=null;
        boolean result=false;
        Connection con = DbPool.getConnection();
        try {
            con.setAutoCommit(false);
            pst = con.prepareStatement(sql);
            dao.execUpdate(con,pst,good.getGoodname(),good.getGoodtype(),good.getPrice(),good.getId());
            result=true;
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            dao.releaseResourse(null,pst,con);
        }
        return result;

    }
    public boolean deleteGood(int id){
        String sql="delete from good where id=?";
        PreparedStatement pst=null;
        boolean result=false;
        Connection con = DbPool.getConnection();
        try {
            con.setAutoCommit(false);
            pst = con.prepareStatement(sql);
            dao.execUpdate(con,pst,id);
            result=true;
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            dao.releaseResourse(null,pst,con);
        }
        return result;

    }
}
