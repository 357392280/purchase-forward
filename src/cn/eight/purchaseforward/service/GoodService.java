package cn.eight.purchaseforward.service;

import cn.eight.purchaseforward.pojo.Good;

import java.util.List;

public interface GoodService {
   List<String> findAllgoodtype();
   List<Good> findAllgood(String goodType);
}
