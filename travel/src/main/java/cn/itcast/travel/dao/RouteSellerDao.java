package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface RouteSellerDao {

    /**
     * 通过Sid查询对应的Seller对象
     * @param sid
     * @return
     */
    Seller findByRid(int sid);
}
