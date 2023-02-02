package org.msy.servlet;

import com.alibaba.fastjson.JSON;
import org.msy.bean.Champion;
import org.msy.bean.Hero;
import org.msy.service.ChampionService;
import org.msy.service.ChampionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Msy
 * @date 2023/2/2  14:32
 */
@WebServlet("/champion")
public class ChampionServlet extends HttpServlet {
    private ChampionService chS = new ChampionServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String m = req.getParameter("m");
        if ("query".equals(m)){
            query(req, resp);
        }else if ("getHero".equals(m)){
            getHero(req, resp);
        }else if ("update".equals(m)){
            update(req, resp);
        }else if ("queryById".equals(m)){
            queryById(req, resp);
        }else if ("delete".equals(m)){
            delete(req,resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("进入DELETE");
        String id = req.getParameter("id");
        System.out.println("获取ID"+id);
        int i = chS.delete(id);
        resp.getWriter().print(i>0);
    }

    private void queryById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Champion c = chS.queryById(id);
        String s = JSON.toJSONString(c);
        resp.getWriter().print(s);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int star = Integer.parseInt(req.getParameter("star"));
        int pid = Integer.parseInt(req.getParameter("pid"));
        String nickname = req.getParameter("nickname");
        Hero hero = new Hero(pid, "");
        Champion champion = new Champion(id, name, star, nickname, hero);
        int i = chS.update(champion);

        resp.getWriter().print(i > 0);
    }

    private void getHero(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArrayList<Hero> list = chS.getHero();
        String s = JSON.toJSONString(list);
        System.out.println(s);
        resp.getWriter().print(s);
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Champion> list = chS.queryAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("msy/list.jsp").forward(req,resp);
    }
}
