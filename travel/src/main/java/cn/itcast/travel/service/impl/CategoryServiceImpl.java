package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    //获取全局变量Dao
    private CategoryDao dao = new CategoryDaoImpl();
    //获取全局变量Redis
    private Jedis jedis = JedisUtil.getJedis();

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Category> findAll() {
        //Set<String> categories= jedis.zrange("category", 1, -1);
        //从redis中查询带有分数的数据
        Set<Tuple> categories = jedis.zrangeWithScores("category", 0, -1);
        //创建未排序List集合
        List<Category> unsorted_list;
        //判断categories是否为空
        if (categories == null || categories.size() == 0) {
            //说明Rides中没有数据，调用Dao查询数据库
            unsorted_list = dao.findAll();
            //将数据存入Redis
            for (Category category : unsorted_list) {
                jedis.zadd("category",category.getCid(),category.getCname());
            }
            //将存入的数据再取一次
            categories = jedis.zrangeWithScores("category", 0, -1);
        }
        //创建排序后List集合
        List<Category> sorted_list = new ArrayList<>();
        //遍历Set集合，取出存入的字符串，存入List集合
        for (Tuple tuple : categories) {
            Category cg = new Category();
            cg.setCname(tuple.getElement());
            cg.setCid((int)tuple.getScore());
            sorted_list.add(cg);
        }
        return sorted_list;
    }
}
