package cn.eight.purchaseforward.service;

import cn.eight.purchaseforward.pojo.Good;

import java.util.List;

public interface GoodService {
    boolean addgood(Good good);
    List<Good> findGoodCritrnia(Good good);
    List<Good> findGoodAll(int pageNow,int pageSize);
    int findTotalRecond();
    boolean modifyGood(Good good);
    boolean removeGood(int id);
}
