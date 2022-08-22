package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImgDaoImpl implements RouteImgDao {

    //定义全局变量Template
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 通过Rid查询对应的RouteImg对象集合
     * @param rid
     * @return
     */
    @Override
    public List<RouteImg> findByRid(int rid) {
        //定义sql
        String sql = "select * from tab_route_img where rid = ? ";
        //执行sql
        return template.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
    }
}
