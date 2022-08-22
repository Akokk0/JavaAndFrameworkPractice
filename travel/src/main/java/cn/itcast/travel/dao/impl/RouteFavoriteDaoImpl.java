package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteFavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.MyFavorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class RouteFavoriteDaoImpl implements RouteFavoriteDao {

    //定于全局变量Template
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 通过rid,uid查询用户是否收藏
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public Favorite isFavorite(int rid, int uid) {
        //定义sql
        String sql = "select * from tab_favorite where rid = ? and uid = ? ";
        //执行sql
        Favorite favorite = null;
        try {
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
            System.out.println("未查询到用户收藏！");
        }
        return favorite;
    }

    /**
     * 通过rid查询收藏次数
     * @param rid
     * @return
     */
    @Override
    public int findCountByRid(int rid) {
        //定义sql
        String sql = "select count(*) from tab_favorite where rid = ?";
        //执行sql
        return template.queryForObject(sql, Integer.class, rid);
    }

    /**
     * 通过uid给对应用户添加对应rid收藏
     * @param rid
     * @param uid
     */
    @Override
    public void addFavorite(int rid, int uid) {
        //定义sql
        String sql = "insert into tab_favorite values(?, ?, ?)";
        //执行sql
        template.update(sql, rid, new Date(), uid);
    }

    /**
     * 通过uid给对应用户移除对应rid收藏
     * @param rid
     * @param uid
     */
    @Override
    public void removeFavorite(int rid, int uid) {
        //定义sql
        String sql = "delete from tab_favorite where rid = ? and uid = ?";
        //执行sql
        template.update(sql, rid, uid);
    }

    /**
     * 通过uid查找用户对应的收藏并返回List集合
     * @param uid
     * @return
     */
    @Override
    public List<MyFavorite> findMyFavorite(int uid) {
        //定义sql
        String sql = "select * from tab_favorite where uid = ?";
        List<MyFavorite> favorites = null;
        try {
            //执行sql
            favorites = template.query(sql, new BeanPropertyRowMapper<>(MyFavorite.class), uid);
        } catch (DataAccessException e) {
        }
        //返回list集合
        return favorites;
    }
}
