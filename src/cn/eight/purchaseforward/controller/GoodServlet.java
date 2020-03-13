package cn.eight.purchaseforward.controller;

import cn.eight.purchaseforward.pojo.CarBean;
import cn.eight.purchaseforward.pojo.Good;
import cn.eight.purchaseforward.service.GoodService;
import cn.eight.purchaseforward.service.impl.GoodServiceImp;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet( "/qiantai/goodservlet")
public class GoodServlet extends HttpServlet {
    private GoodService goodService=new GoodServiceImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String reqType=request.getParameter("reqType");
       if (reqType.equals("main")){
           openMain(request,response);
       }else if (reqType.equals("downImg")){
           downImg(request,response);
       }else if (reqType.equals("addCar")){
           addCar(request,response);
       }else if (reqType.equals("delCar")){
           delCar(request,response);
       }else if (reqType.equals("clearCar")){
           clearCar(request,response);
       }else if (reqType.equals("modCar")){
           modCar(request,response);
       }else if (reqType.equals("flow")){
           flow(request,response);
       }else if (reqType.equals("calculate")){
           calculate(request,response);
       }
    }

    private void calculate(HttpServletRequest request, HttpServletResponse response) {

    }


    private void flow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CarBean carBean=(CarBean)session.getAttribute("car");
        List<Good> goodlist=goodService.findCars(carBean);
        request.setAttribute("car",goodlist);
        request.getRequestDispatcher("flow.jsp").forward(request,response);
    }

    private void modCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] goodids = request.getParameterValues("goodids");
        String[] amounts = request.getParameterValues("amounts");
        Integer[] goodids_int=new Integer[goodids.length];
        Integer[] amounts_int=new Integer[amounts.length];
        for (int i = 0; i < goodids.length; i++) {
            goodids_int[i]=Integer.valueOf(goodids[i]);
            amounts_int[i]=Integer.valueOf(amounts[i]);
        }
        HttpSession session = request.getSession();
        CarBean carBean=(CarBean)session.getAttribute("car");
        carBean.modGood(goodids_int,amounts_int);;

        genericCardate(request,response,carBean,session);
    }

    private void clearCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CarBean carBean=(CarBean)session.getAttribute("car");
        carBean.clearCar();

        genericCardate(request,response,carBean,session);
    }

    private void delCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id=Integer.valueOf(request.getParameter("goodid"));
        HttpSession session = request.getSession();
        CarBean carBean=(CarBean)session.getAttribute("car");
        carBean.removeGood(id);
        genericCardate(request,response,carBean,session);
    }

    private void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id=Integer.valueOf(request.getParameter("goodid"));
        HttpSession session = request.getSession();
        CarBean carBean=(CarBean)session.getAttribute("car");
        if (carBean==null){
            carBean=new CarBean();
        }
        carBean.addGood(id);

        genericCardate(request,response,carBean,session);

    }
     //把    carbean对象中的购物车的map对象转换成能够在页面上展现的list<good>集合对象
    private void genericCardate(HttpServletRequest request, HttpServletResponse response,CarBean carBean,HttpSession session) throws ServletException, IOException {
        session.setAttribute("car",carBean);
        List<Good> goodlist=goodService.findCars(carBean);
        request.setAttribute("car",goodlist);
        request.getRequestDispatcher("flow.jsp").forward(request,response);
    }
    private void downImg(HttpServletRequest request, HttpServletResponse response) {
        String filename=request.getParameter("filename");
        String path=request.getServletContext().getRealPath("/WEB-INF/upload/"+filename);
        FileInputStream is=null;
        ServletOutputStream os = null;
        try {
            int len=0;
            byte[] buff=new byte[1024];
             is=new FileInputStream(path);
            os=response.getOutputStream();
             while ((len=is.read(buff))!=-1){
                 os.write(buff,0,len);
             }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodType=request.getParameter("goodType");
        List<String> goodTypes=goodService.findAllgoodtype();
        List<Good> goodList=null;
        if (goodTypes.size()>0){
            if (goodType==null||goodType.isEmpty())
            {
            goodType=goodTypes.get(0);
            }
             goodList=goodService.findAllgood(goodType);
        }
        HttpSession session = request.getSession();
        CarBean carBean=(CarBean)session.getAttribute("car");
        int amount=0;
        int blances=0;
        if (carBean!=null){
            amount=carBean.getAmouts();
            blances=carBean.getBlance();
        }
        System.out.println(amount);
        request.setAttribute("amounts",amount);
        request.setAttribute("blances",blances);
        request.setAttribute("goodtypes",goodTypes);
        request.setAttribute("goodlist",goodList);
        request.getRequestDispatcher("main.jsp").forward(request,response);

    }
}
