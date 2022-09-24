package Controller.Filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "loginFilter",urlPatterns = "/*")
public class loginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        List<String> list= new ArrayList<String>(); //将不需要过滤的请求存放到集合中
        list.add("register.jsp");//注册页面
        list.add("registerServlet");//注册页面
        list.add("login.jsp");//登录页面
        list.add("loginServlet");//登录页面
        list.add("loginFail.jsp");//登录失败
        list.add("index.jsp");//首页
        list.add("goods.jsp");//商品列表

        String path = req.getServletPath();//得到请求的url
        //循环集合把不需要过滤的请求放行
        for(String l : list){
            if(path.endsWith(l)){
//                System.out.println("放行");
                chain.doFilter(request, response);
                return;
            }
        }
        //查看session是否存在对象，有对象的话也不需要过滤
        String userPhone = (String) req.getSession().getAttribute("userPhone");
        if(userPhone==null){
            //session没有对象就跳转到登录页面
            res.sendRedirect("/demo_war_exploded/login.jsp");
        }else {
            chain.doFilter(request, response);
        }
    }
}
