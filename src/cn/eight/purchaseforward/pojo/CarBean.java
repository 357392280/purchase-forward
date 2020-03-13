package cn.eight.purchaseforward.pojo;

import cn.eight.purchaseforward.service.GoodService;
import cn.eight.purchaseforward.service.impl.GoodServiceImp;

import java.util.HashMap;
import java.util.List;
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
    public Integer getAmouts(){
        int amouts=0;
        if (car!=null){
            for (Map.Entry<Integer,Integer> entry:car.entrySet()) {
                amouts+=entry.getValue();
            }
        }
        return amouts;
    }
    public Integer getBlance(){
        int blances=0;
        GoodService service=new GoodServiceImp();
        List<Good> cars = service.findCars(this);
        for (int i = 0; i < cars.size(); i++) {
            blances+=cars.get(i).getAmout()*cars.get(i).getPrice();
        }
        return blances;
    }
}
