package cn.eight.purchaseforward.controller;

import cn.eight.purchaseforward.pojo.User;
import cn.eight.purchaseforward.service.UserService;
import cn.eight.purchaseforward.service.impl.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet( "/qiantai/userservlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String reqType=request.getParameter("reqType");
       if (reqType.equals("reg")){
           registerUser(request,response);
       }else if (reqType.equals("checkuser")){
           checkUser(request,response);
       }
    }

    private void checkUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username=request.getParameter("username");
        UserService userService=new UserServiceImp();
        boolean result = userService.checkUser(username);
        response.getWriter().print(result);
        response.getWriter().flush();

    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username=request.getParameter("username");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String confirm_password=request.getParameter("confirm_password");
        String qqcode=request.getParameter("qqcode");
        User user=new User(username,password,null,email,qqcode);
        UserService userService=new UserServiceImp();
        boolean result = userService.registerUser(user);

        if(result){
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            response.sendRedirect("main.jsp");

        }else {
            request.setAttribute("info","注册失败");
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }

    }
}
