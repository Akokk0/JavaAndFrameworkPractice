package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    //定义成员变量Template
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 查询总页数
     * @param cid
     * @return
     */
    @Override
    public int findTotalCount(int cid, String rname) {
        //定义sql模板
        String sql = "select count(*) from tab_route where 1 = 1 ";
        //创建StringBuilder对象
        StringBuilder sb = new StringBuilder();
        //将模板添加进去
        sb.append(sql);
        //创建List集合装载参数
        List param = new ArrayList();
        //判断cid是否存在
        if (cid != 0) {
            sb.append(" and cid = ? ");
            param.add(cid);
        }
        //判断rname是否存在
        if (rname != null) {
            sb.append(" and rname like ?");
            param.add("%" + rname + "%");
        }
        //转换sql
        sql = sb.toString();
        //执行sql
        Integer totalCount = template.queryForObject(sql, Integer.class, param.toArray());

        return totalCount;
    }

    @Override
    public List<Route> pageQuery(int cid, int start, int pageSize, String rname) {
        //定义sql模板
        String sql = "select * from tab_route where 1 = 1 ";
        //创建StringBuilder对象
        StringBuilder sb = new StringBuilder();
        //将sql添加进sb
        sb.append(sql);
        //创建List集合装载参数
        List param = new ArrayList();
        //判断cid是否存在
        if (cid != 0) {
            sb.append(" and cid = ? ");
            param.add(cid);
        }
        //判断rname是否存在
        if (rname != null) {
            sb.append(" and rname like ? ");
            param.add("%" + rname + "%");
        }
        //将开始和每页显示数添加到sql，并且将参数添加到list集合中
        sb.append(" limit ? , ? ");
        param.add(start);
        param.add(pageSize);
        //将sb转换为字符串
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(param);
        //执行Sql
        List<Route> routes = template.query(sql, new BeanPropertyRowMapper<>(Route.class), param.toArray());
        return routes;
    }

    /**
     * 通过rid查询对应的Route对象
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        //定义sql
        String sql = "select * from tab_route where rid = ? ";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
    }

    /**
     * 查找用户收藏的总记录数
     * @param myFavorites
     * @return
     */
    @Override
    public int findFavoriteTotalCount(List<Integer> myFavorites) {
        //定义sql模板
        String sql = "select count(*) from tab_route where ";
        //创建StringBuilder对象
        StringBuilder sb = new StringBuilder();
        //将sql模板添加进去
        sb.append(sql);
        //遍历循环有几个route对象就循环几次
        for (int i = 0; i < myFavorites.size(); i++) {
            if (i == 0) {
                //说明是第一次拼字符串，所以不加or
                sb.append(" rid = ? ");
                continue;
            }
            //之后每一次循环都添加一次or rid = ?
            sb.append(" or rid = ? ");
        }
        //将sb转换为字符串
        sql = sb.toString();
        //执行sql
        return template.queryForObject(sql, Integer.class, myFavorites.toArray());
    }

    /**
     * 查找用户收藏的所有Route对象并分页展示
     * @param myFavorites
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Route> findFavoriteByPageQuery(List<Integer> myFavorites, int start, int pageSize) {
        //定义sql模板
        String sql = "select * from tab_route where ";
        //创建StringBuilder对象
        StringBuilder sb = new StringBuilder();
        //将sql模板添加进去
        sb.append(sql);
        //遍历循环有几个route对象就循环几次
        for (int i = 0; i < myFavorites.size(); i++) {
            if (i == 0) {
                //说明是第一次拼字符串，所以不加or
                sb.append(" rid = ? ");
                continue;
            }
            //之后每一次循环都添加一次or rid = ?
            sb.append(" or rid = ? ");
        }
        //最后拼接sql limit
        sb.append(" limit ? , ? ");
        //将sb转换为字符串
        sql = sb.toString();
        //将查询起始和查询个数添加进List集合
        myFavorites.add(start);
        myFavorites.add(pageSize);
        //执行sql
        return template.query(sql, new BeanPropertyRowMapper<>(Route.class), myFavorites.toArray());
    }
}
