package cn.eight.purchaseforward.pojo;

import java.util.HashMap;
import java.util.Map;

public class CarBean {
    //定义属性存放购物车的关键数据
    Map<Integer,Integer> car;

    public Map<Integer, Integer> getCar() {
        return car;
    }

    public void setCar(Map<Integer, Integer> car) {
        this.car = car;
    }
    //添加商品
    public void addGood(Integer id){
        if(car==null){
            car=new HashMap<>();
        }
        car.put(id,1);
    }

    //清空
    public void clearCar(){
        if (car!=null){
            car.clear();
        }
    }
    //删除商品

    public void removeGood(Integer id){
        if (car!=null){
            car.remove(id);
        }
    }
    //修改商品数量
    public void modGood(Integer[] ids,Integer[] amouts){
        for (int i = 0; i < ids.length; i++) {
            car.put(ids[i],amouts[i]);
        }
    }
}
