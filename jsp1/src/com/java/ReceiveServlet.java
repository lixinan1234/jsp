package com.java;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:lixinan
 * @email:2489460735@qq.com
 * @desc:
 * @datetime: 2024/5/8 16:46
 */

@WebServlet("/receiveServlet")
public class ReceiveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");//设置响应数据的内容的类型

        String name = req.getParameter("name");//从客户端获取参数为name的值
        String gen = req.getParameter("gen");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String mail = req.getParameter("mail");
        String address = req.getParameter("address");
        String school = req.getParameter("school");
        String work = req.getParameter("work");
        String skill = req.getParameter("skill");

        Resume resume = new Resume(name, gen, birthday, phone, mail, address, school, work, skill);
        ResumeDAO resumeDAO = new ResumeDAO();
        boolean isOk = resumeDAO.insertResume(resume);

        //如果isOk为true，表示插入操作成功，代码会将简历对象设置为请求属性，
        //并将请求转发到index.jsp页面进行后续处理。

        //如果isOk为false，表示插入操作失败，代码会向响应对象输出"提交失败!!!"的消息
        if (isOk){
            req.setAttribute("resume",resume);
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }else {
            resp.getWriter().print("提交失败!!!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}