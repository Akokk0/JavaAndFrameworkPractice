package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {

    /**
     * 查询总记录数
     * @param cid
     * @return
     */
    int findTotalCount(int cid, String rname);

    /**
     * 分页查询
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    List<Route> pageQuery(int cid, int start, int pageSize, String rname);

    /**
     * 通过rid查找一条记录
     * @param rid
     * @return
     */
    Route findOne(int rid);

    /**
     * 查找用户收藏的总记录数
     * @param myFavorites
     * @return
     */
    int findFavoriteTotalCount(List<Integer> myFavorites);

    /**
     * 查找用户收藏的所有Route对象并分页展示
     * @param myFavorites
     * @param start
     * @param pageSize
     * @return
     */
    List<Route> findFavoriteByPageQuery(List<Integer> myFavorites, int start, int pageSize);
}
