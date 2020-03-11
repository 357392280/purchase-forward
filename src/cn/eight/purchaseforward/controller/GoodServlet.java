package cn.eight.purchaseforward.controller;

import cn.eight.purchaseforward.pojo.Good;
import cn.eight.purchaseforward.service.GoodService;
import cn.eight.purchaseforward.service.impl.GoodServiceImp;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
       }
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
        request.setAttribute("goodtypes",goodTypes);
        request.setAttribute("goodlist",goodList);
        request.getRequestDispatcher("main.jsp").forward(request,response);
    }
}
