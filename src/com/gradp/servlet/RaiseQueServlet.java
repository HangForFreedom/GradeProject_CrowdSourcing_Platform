package com.gradp.servlet;

import com.gradp.bean.ClassesBean;
import com.gradp.bean.UserBean;
import com.gradp.biz.QuestionBiz;
import com.gradp.biz.UserBiz;
import com.gradp.util.EmojiUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>摘要：</p>
 * <p>备注：</p>
 */
public class RaiseQueServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");

        UserBiz ubz = new UserBiz();
        QuestionBiz quebz = new QuestionBiz();

        //提出问题数量
        int quesum = ubz.queryQuestionById(ub.getUserid());
        //回答数量
        int anssum = ubz.queryAnswerById(ub.getUserid());

        //查询所有分类
        List<ClassesBean> classbs = quebz.queryAllClasses();
        req.setAttribute("classbs", classbs);
        req.setAttribute("quesum", quesum);
        req.setAttribute("anssum", anssum);

        req.getRequestDispatcher("raiseQue.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        // doGet(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");

        //创建工厂类
        DiskFileItemFactory dfif = new DiskFileItemFactory();

        //创建文件上传对象
        ServletFileUpload sfu = new ServletFileUpload(dfif);
        Map texthm = new HashMap<>();
        try {
            //获取请求中的数据
            List<FileItem> fileItem = sfu.parseRequest(req);
            for (FileItem f : fileItem){
                if (f.isFormField()){
                    //文本
                    texthm.put(f.getFieldName(), EmojiUtil.emojiToString(f.getString("UTF-8")));
                }else{
                    //图片
                    //图片的上传目录
                    String txPath = req.getSession().getServletContext().getRealPath("/upload");
                    System.out.println(txPath);
                    File file = new File(txPath);
                    //判断文件是否存在
                    if (!file.exists()) {
                        //创建
                        file.mkdirs();
                    }
                    String picpath=null;
                    if (f.getSize()>0) {
                        String imgname = f.getName();
                        picpath = "upload/"+imgname;
                        //写入
                        f.write(new File(txPath+"/"+imgname));
                        f.delete();
                    }
                    texthm.put("pic", picpath);
                    System.out.println(picpath);
                }
            }
        }catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        texthm.put("userid", ub.getUserid());
        UserBiz ubz = new UserBiz();
        QuestionBiz quebz = new QuestionBiz();
        //从HashMap获取key对应的value：queScore，赋予对象
        Object obj = texthm.get("queScore");
        //获取对象queScore字符串
        String queScore = obj.toString();
        if (Integer.parseInt(queScore) <= ub.getScore()){

            // select score from user where userId = ?
            // int ? = score - queScore;
            // update user set score=? where userId = ?
            //查询用户积分（可写可不写）
            int userScore = ubz.queryUserScoreById(ub.getUserid());
            int resultScore = userScore - Integer.parseInt(queScore);
            //更改用户积分
            ubz.updateUserScoreById(resultScore, ub.getUserid());

            //清空session（不可清空！）
            // req.getSession().invalidate();

            //重新根据userid查询遍历数据库中用户信息
            UserBean ubean = new UserBean();
            ubean.setUserid(ub.getUserid());
            UserBean newub = ubz.queryAll(ubean);
            session.removeAttribute("ub"); //移出旧的session，需要添加重新查询的
            session.setAttribute("ub", newub);

            // 数据库插入，发布问题
            int result = quebz.raiseQuestion(texthm);
            if (result!=0){
                resp.sendRedirect("main.do");
            }
        }else{
            req.setAttribute("msg", "积分不足，请回答问题获取积分或者签到获取积分");
            req.getRequestDispatcher("raiseQue.jsp").forward(req, resp);
        }

    }
}
