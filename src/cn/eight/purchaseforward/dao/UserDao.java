package cn.eight.purchaseforward.dao;

import cn.eight.purchaseforward.pojo.User;
import cn.eight.purchaseforward.util.DbPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private BasicDao dao=new BasicDao();

    public boolean queryUserByUsername(String username){
        ResultSet rs=null;
        PreparedStatement pst=null;
        boolean result=false;
        Connection con= DbPool.getConnection();
        String sql="select count(*) from user where username=?  and rule=1";
        try {
             pst = con.prepareStatement(sql);
             rs = dao.execQuery(con, pst,username);
            if (rs!=null&&rs.next()&&rs.getInt(1)==1){
                result=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dao.releaseResourse(rs,pst,con);
        }

        return result;
    }
    public boolean insertUser(User user){
        boolean result=false;
        String sql="insert into user(username,password,rule,email,qq) values (?,?,1,?,?)";
        Connection con = DbPool.getConnection();
        PreparedStatement pst=null;
        try {
            con.setAutoCommit(false);
             pst = con.prepareStatement(sql);
            dao.execUpdate(con,pst,user.getUsername(),user.getPassword(),user.getEmail(),user.getQq());
            con.commit();
            result=true;
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
