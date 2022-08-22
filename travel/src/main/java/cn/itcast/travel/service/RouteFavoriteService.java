package cn.itcast.travel.service;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.MyFavorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteFavoriteService {

    /**
     * 通过Dao查询rid,uid判断用户是否收藏
     * @param rid
     * @param uid
     * @return
     */
    boolean isFavorite(String rid, int uid);

    /**
     * 给对应的uid添加对应rid收藏
     * @param rid
     * @param uid
     */
    void addFavorite(String rid, int uid);

    /**
     * 给对应的uid移除移除对应rid收藏
     * @param rid
     * @param uid
     */
    void removeFavorite(String rid, int uid);

    /**
     * 通过Uid查询用户收藏的路线
     * @param uid
     * @return
     */
    List<MyFavorite> findMyFavorite(int uid);

    /**
     * 根据查询到的rid数据集合查询符合条件的路线
     * @param myFavorites
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    PageBean<Route> pageQuery(List<Integer> myFavorites, int currentPage, int pageSize, String rname);
}
