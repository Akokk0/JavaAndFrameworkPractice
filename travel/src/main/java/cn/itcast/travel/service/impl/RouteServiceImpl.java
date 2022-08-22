package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteFavoriteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.RouteSellerDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteFavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.RouteSellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    //定义全局Dao
    RouteDao dao = new RouteDaoImpl();
    RouteImgDao routeImgDao = new RouteImgDaoImpl();
    RouteSellerDao routeSellerDao = new RouteSellerDaoImpl();
    RouteFavoriteDao favoriteDao = new RouteFavoriteDaoImpl();

    /**
     * 按分类分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //创建PageBean对象
        PageBean<Route> pb = new PageBean<>();
        //设置页面页数
        pb.setPageSize(pageSize);
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //获取总记录数
        int totalCount = dao.findTotalCount(cid, rname);
        //设置总记录数
        pb.setTotalCount(totalCount);
        //获取总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        //设置总页数
        pb.setTotalPage(totalPage);
        //获取List集合
        //获取start
        int start = (currentPage - 1) * pageSize;
        List<Route> routes = dao.pageQuery(cid, start, pageSize, rname);
        //封装PB对象
        pb.setList(routes);
        return pb;
    }

    /**
     * 通过rid查询对应的Route对象
     * @param rid
     * @return
     */
    @Override
    public Route findOne(String rid) {
        //通过RouteDao查询对应Route对象
        Route route = dao.findOne(Integer.parseInt(rid));

        //通过RouteImgDao查询图片List集合
        List<RouteImg> routeImgs = routeImgDao.findByRid(Integer.parseInt(rid));
        route.setRouteImgList(routeImgs);

        //通过RouteSellerDao查询商家信息
        Seller seller = routeSellerDao.findByRid(route.getSid());
        route.setSeller(seller);

        //通过RouteFavoriteDao查询收藏次数
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);

        //将Route对象返回
        return route;
    }
}
