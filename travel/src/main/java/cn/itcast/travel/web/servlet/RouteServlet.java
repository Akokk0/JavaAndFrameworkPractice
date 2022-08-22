package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.RouteFavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteFavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    //定义成员变量service
    private RouteService service = new RouteServiceImpl();
    private RouteFavoriteService favoriteService = new RouteFavoriteServiceImpl();

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rnameStr = request.getParameter("rname");
        rnameStr = new String(rnameStr.getBytes("iso-8859-1"), "utf-8");

        //判断获取的参数是否符合要求
        int cid = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equalsIgnoreCase(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }

        int pageSize;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 10;
        }

        int currentPage;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        String rname = null;
        if (rnameStr != null && rnameStr.length() > 0 && !"null".equalsIgnoreCase(rnameStr)) rname = rnameStr;

        //调用service的pageQuery方法查询pageBean对象
        PageBean<Route> pb = service.pageQuery(cid, currentPage, pageSize, rname);

        //将pb序列化并返回
        returnJson(pb,response);
    }

    /**
     * 查询一个路线详情
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数rid
        String rid = request.getParameter("rid");
        //调用service中findOne方法查询
        Route route = service.findOne(rid);
        //调用父类方法将Json数据传回去
        returnJson(route, response);
    }

    /**
     * 查询数据库判断用户是否收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数rid
        String rid = request.getParameter("rid");
        //获取user对象
        User user = (User) request.getSession().getAttribute("user");
        //判断用户是否登录
        int uid;
        if (user == null) {
            //用户尚未登录
            uid = 0;
        } else {
            //用户已经登录
            uid = user.getUid();
        }
        //调用RouteFavoriteService中isFavorite方法判断用户是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);
        //将flag写回页面
        returnJson(flag, response);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数rid
        String rid = request.getParameter("rid");
        //获取user对象
        User user = (User) request.getSession().getAttribute("user");
        //判断用户是否登录
        int uid;
        if (user == null) {
            //用户尚未登录
            return;
        } else {
            //用户已经登录
            uid = user.getUid();
        }
        //调用service中方法addFavorite添加收藏
        favoriteService.addFavorite(rid, uid);
    }

    public void removeFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数rid
        String rid = request.getParameter("rid");
        //获取user对象
        User user = (User) request.getSession().getAttribute("user");
        //判断用户是否登录
        int uid;
        if (user == null) {
            //用户尚未登录
            return;
        } else {
            //用户已经登录
            uid = user.getUid();
        }
        //调用service中方法addFavorite添加收藏
        favoriteService.removeFavorite(rid, uid);
    }

    public void myFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取user对象
        User user = (User) request.getSession().getAttribute("user");
        //判断用户是否登录
        int uid;
        if (user == null) {
            //用户尚未登录
            return;
        } else {
            //用户已经登录
            uid = user.getUid();
        }
        //获取参数
        String pageSizeStr = request.getParameter("pageSize");
        String currentPageStr = request.getParameter("currentPage");
        String rnameStr = request.getParameter("rname");

        //调用service中方法myFavorite查询我的收藏
        List<MyFavorite> myFavorites = favoriteService.findMyFavorite(uid);
        //创建List集合存储rid
        List<Integer> myFavoritesRid = new ArrayList<>();
        //遍历数组
        for (MyFavorite myFavorite : myFavorites) {
            int rid = myFavorite.getRid();
            myFavoritesRid.add(rid);
        }
        //判断参数是否符合要求
        int pageSize;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 12;
        }
        int currentPage;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        String rname = null;
        if (rnameStr != null && rnameStr.length() > 0 && !"null".equalsIgnoreCase(rnameStr)) rname = rnameStr;

        //调用favoriteService的pageQuery方法查询pageBean对象
        PageBean<Route> pb = favoriteService.pageQuery(myFavoritesRid, currentPage, pageSize, rname);
        //调用父类方法将List转换为Json对象并返回
        returnJson(pb, response);
    }
}