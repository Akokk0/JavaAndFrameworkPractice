package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.MyFavorite;

import java.util.List;

public interface RouteFavoriteDao {

    /**
     * 通过rid,uid查询用户是否收藏
     * @param rid
     * @param uid
     * @return
     */
    Favorite isFavorite(int rid, int uid);

    /**
     * 通过rid查询收藏次数
     * @param rid
     * @return
     */
    int findCountByRid(int rid);

    /**
     * 通过uid给对应用户添加对应rid收藏
     * @param rid
     * @param uid
     */
    void addFavorite(int rid, int uid);

    /**
     * 通过uid给对应用户移除对应rid收藏
     * @param rid
     * @param uid
     */
    void removeFavorite(int rid, int uid);

    /**
     * 通过uid查找用户对应的收藏并返回List集合
     * @param uid
     * @return
     */
    List<MyFavorite> findMyFavorite(int uid);
}
