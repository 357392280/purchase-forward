package cn.eight.purchaseforward.service.impl;

import cn.eight.purchaseforward.dao.GoodDao;
import cn.eight.purchaseforward.pojo.Good;
import cn.eight.purchaseforward.service.GoodService;

import java.util.List;

public class GoodServiceImp implements GoodService {
    private GoodDao goodDao=new GoodDao();

    @Override
    public List<String> findAllgoodtype() {
        return goodDao.queryGoodType();
    }

    @Override
    public List<Good> findAllgood(String goodType) {
        return goodDao.queryGoodByType(goodType);
    }
}
