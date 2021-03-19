package com.shuai.controller;


import com.shuai.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


/*
1.第一步告诉SpringMVC这是一个处理器,可以处理请求
/
 */

@RequestMapping("/helloCon")
@Controller
public class HelloController {
    @Autowired
    private Book book;

    public HelloController() {
        System.out.println("controler!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    //    /代表从当前项目下处理请求
    @RequestMapping("/hello")
    public String myFirstRequest(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("请求已经收到了");
        System.out.println(request.getPathInfo());
        return "success";
//       return "forward:/WEB-INF/222.html";
    }
    @RequestMapping(value = "/hello2/{id}")
    public String mySecondRequest( HttpServletRequest request,@PathVariable("id") Integer id)
    {
        System.out.println("请求已经收到了2");
        System.out.println("这个是pathvariable获取的id" + id);
        System.out.println("这个是request获取的id" + request.getParameter("id"));
        return "success";
//       return "forward:/WEB-INF/222.html";
    }

    @RequestMapping("/hello3")
    public String myThirdRequest(HttpServletRequest request, HttpServletResponse response, Book book)
    {
        System.out.println("请求已经收到了");
        System.out.println("this is the book name from the form" + book.getName());
        System.out.println("this is the book price from the form" + book.getPrice());
        System.out.println("this is the book amount from the form" + book.getAmount());

        ServletContext context = request.getSession().getServletContext();
        context.setAttribute("test1","this is my first set");
        return "success";
//       return "forward:/WEB-INF/222.html";
    }

    @RequestMapping("/hello4")
    public String myFourthRequest(HttpServletRequest request, HttpServletResponse response, Book book)
    {

        ServletContext context = request.getSession().getServletContext();

        String test = (String) context.getAttribute("test1");

        System.out.println(test);
        System.out.println("this is from hello4" + System.identityHashCode(this));
        return "success";
//       return "forward:/WEB-INF/222.html";
    }
//    @ResponseBody
    @RequestMapping(value = "/hello5")
    public String myFifthRequest(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ServletContext context = request.getSession().getServletContext();

        String test = (String) context.getAttribute("test1");
        model.addAttribute("model","this is from model");
        Object obj = new HttpRequestHandlerAdapter();

        System.out.println(obj.getClass().getName());

        System.out.println("当前方法名"+ Thread.currentThread().getStackTrace()[1].getMethodName());
//        Class clazz = Class.forName(this.getClass().getName());
//        Method me = clazz.getDeclaredMethod(Thread.currentThread().getStackTrace()[1].getMethodName(),HttpServletRequest.class,HttpServletResponse.class,Model.class);
//        me.invoke(this,request,response,model);

        System.out.println(test);

        System.out.println(request.getContextPath());
        Collection<Book> coll = new ArrayList<>();
        coll.add(book);

        System.out.println("this is from hello5"+SpringMVCContextHolder.getBean("helloController"));
//        return coll;
        return "success";
//       return "forward:/WEB-INF/222.html";
    }

    @RequestMapping("/hello6")
    public String mySixthRequest(HttpServletRequest request, HttpServletResponse response, Book book)
    {
//        获取服务器的context
        ServletContext sc = request.getSession().getServletContext();

//        获取spring的context
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(sc);

//        获取spring mvc的context
        WebApplicationContext springMVCContext = RequestContextUtils.findWebApplicationContext(request);


        System.out.println("this is from spring ioc of book" + springContext.getBean(Book.class));

//        因为HelloController是在spring mvc容器中,因此无法渠道=取到
//        System.out.println("this is from spring ioc of HelloController" + springContext.getBean(HelloController.class));

        System.out.println("this is from spring mvc ioc of HelloController" + springMVCContext.getBean(HelloController.class));
        System.out.println("this is from spring mvc ioc of book" + springMVCContext.getBean(Book.class));
        return "success";
//       return "forward:/WEB-INF/222.html";
    }


}
