package org.msy.dao;

import org.msy.bean.Champion;
import org.msy.bean.Hero;
import org.msy.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Msy
 * @date 2023/2/2  14:30
 */
public class ChampionDao {
    Connection conn = JDBCUtil.getConn();
    public ArrayList<Champion> queryAll() {
        ArrayList<Champion> list = new ArrayList<>();
        String s = "select c.*,h.career from championsword c join hero h on h.id = c.pid";
        try {
            PreparedStatement ps = conn.prepareStatement(s);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Champion(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(5),new Hero(rs.getInt(4),rs.getString(6))));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public ArrayList<Hero> getHero() {
        ArrayList<Hero> heroes = new ArrayList<>();
        String s = "select * from hero";
        try {
            PreparedStatement ps = conn.prepareStatement(s);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                heroes.add(new Hero(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return heroes;
    }

    public Champion queryById(String id) {
        Champion c = null;
        String s = "select c.*,h.career from championsword c join hero h on h.id = c.pid where c.id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(s);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                c = new Champion(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(5),new Hero(rs.getInt(4),rs.getString(6)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }

    public int update(Champion champion) {
        int i = 0;
        try {
            String s = "update championsword set name=?,star=?,pid=?,nickname=? where id= ? ";
            PreparedStatement ps = conn.prepareStatement(s);
            ps.setString(1, champion.getName());
            ps.setInt(2, champion.getStar());
            ps.setInt(3, champion.getHero().getId());
            ps.setString(4, champion.getNickname());
            ps.setInt(5, champion.getId());
            i = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    public int delete(String id) {
        int i = 0;
        String s = "delete from championsword where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(s);
            ps.setString(1,id);
            i = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }
}
