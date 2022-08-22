package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteFavoriteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteFavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.MyFavorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteFavoriteService;

import java.util.List;

public class RouteFavoriteServiceImpl implements RouteFavoriteService {

    //定义全局变量Dao
    private RouteFavoriteDao favoriteDao = new RouteFavoriteDaoImpl();
    private RouteDao dao = new RouteDaoImpl();

    /**
     * 通过Dao查询rid,uid判断用户是否收藏
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public boolean isFavorite(String rid, int uid) {
        //调用dao查询用户是否收藏
        Favorite favorite = favoriteDao.isFavorite(Integer.parseInt(rid), uid);
        //判断favorite对象是否为空
        return favorite != null;
    }

    /**
     * 给对应的Uid添加对应rid收藏
     * @param rid
     * @param uid
     */
    @Override
    public void addFavorite(String rid, int uid) {
        //调用dao中添加收藏方法
        favoriteDao.addFavorite(Integer.parseInt(rid), uid);
    }

    /**
     * 给对应的uid移除移除对应rid收藏
     * @param rid
     * @param uid
     */
    @Override
    public void removeFavorite(String rid, int uid) {
        //调用dao中移除收藏方法
        favoriteDao.removeFavorite(Integer.parseInt(rid), uid);
    }

    /**
     * 通过Uid查询用户收藏的路线
     * @param uid
     * @return
     */
    @Override
    public List<MyFavorite> findMyFavorite(int uid) {
        //调用dao中findMyFavorite方法查询我的收藏并返回List集合
        return favoriteDao.findMyFavorite(uid);
    }

    /**
     * 根据查询到的rid数据集合查询符合条件的路线
     * @param myFavorites
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    @Override
    public PageBean<Route> pageQuery(List<Integer> myFavorites, int currentPage, int pageSize, String rname) {
        //创建PageBean对象
        PageBean<Route> pageBean = new PageBean<>();
        //设置当前页
        pageBean.setCurrentPage(currentPage);
        //设置每页显示数
        pageBean.setPageSize(pageSize);
        //计算总记录数
        int totalCount = dao.findFavoriteTotalCount(myFavorites);
        pageBean.setTotalCount(totalCount);
        //计算总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);
        //计算起始值
        int start = (currentPage - 1) * pageSize;
        //通过dao查询符合条件的route对象
        List<Route> routes = dao.findFavoriteByPageQuery(myFavorites, start, pageSize);
        //将list集合存储到pb中
        pageBean.setList(routes);
        return pageBean;
    }
}
