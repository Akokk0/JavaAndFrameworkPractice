package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteSellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class RouteSellerDaoImpl implements RouteSellerDao {

    //定义全局变量Template
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 通过Sid查询对应的Seller对象
     * @param sid
     * @return
     */
    @Override
    public Seller findByRid(int sid) {
        //定义sql
        String sql = "select * from tab_seller where sid = ? ";
        //执行sql
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), sid);
    }
}
