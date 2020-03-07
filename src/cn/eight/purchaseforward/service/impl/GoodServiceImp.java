package cn.eight.purchaseforward.service.impl;

import cn.eight.purchaseforward.dao.GoodDao;
import cn.eight.purchaseforward.pojo.Good;
import cn.eight.purchaseforward.service.GoodService;

import java.util.List;

public class GoodServiceImp implements GoodService {
    private GoodDao goodDao=new GoodDao();
    @Override
    public boolean addgood(Good good) {
        return goodDao.insertGood(good);
    }

    @Override
    public List<Good> findGoodCritrnia(Good good) {

        return goodDao.queryGoodCritania(good);
    }

    @Override
    public List<Good> findGoodAll(int pageNow, int pageSize) {
        return goodDao.queryGoodAll(pageNow,pageSize);
    }

    @Override
    public int findTotalRecond() {
        return goodDao.queryTotalRecond();
    }

    @Override
    public boolean modifyGood(Good good) {
        return goodDao.modGood(good);
    }

    @Override
    public boolean removeGood(int id) {
        return goodDao.deleteGood(id);
    }
}
