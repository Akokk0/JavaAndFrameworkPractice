package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    /**
     * 通过Rid查询对应的RouteImg对象集合
     * @param rid
     * @return
     */
    List<RouteImg> findByRid(int rid);
}
