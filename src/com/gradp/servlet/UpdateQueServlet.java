package com.gradp.servlet;

import com.gradp.bean.ClassesBean;
import com.gradp.bean.QuestionBean;
import com.gradp.bean.UserBean;
import com.gradp.biz.AnswerBiz;
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
public class UpdateQueServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");
        session.setAttribute("ub", ub);
        UserBiz ubz = new UserBiz();
        QuestionBiz quebz = new QuestionBiz();

        //提出问题数量
        int quesum = ubz.queryQuestionById(ub.getUserid());
        //回答数量
        int anssum = ubz.queryAnswerById(ub.getUserid());

        String queid = req.getParameter("queid");
        if(queid != null)
        {
            session.setAttribute("queid", queid);
        }
        else
        {
            queid = (String) session.getAttribute("queid");
        }

        //根据id查询一条问题进行修改
        List<QuestionBean> quebs = quebz.querySingleQuestion(Integer.parseInt(queid));
        req.setAttribute("quebs", quebs);

        //查询所有分类
        List<ClassesBean> classbs = quebz.queryAllClasses();

        req.setAttribute("classbs", classbs);
        req.setAttribute("quesum", quesum);
        req.setAttribute("anssum", anssum);

        req.getRequestDispatcher("updateQue.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        // doGet(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String queid = (String) session.getAttribute("queid");
        UserBean ub = (UserBean) session.getAttribute("ub");

        QuestionBiz quebz = new QuestionBiz();

        //数据上传
        //创建工厂类
        DiskFileItemFactory dfif = new DiskFileItemFactory();

        //创建文件上传对象
        ServletFileUpload sfu = new ServletFileUpload(dfif);
        Map texthm = new HashMap<>();
        try {
            //获取请求中的数据
            List<FileItem> fileItem = sfu.parseRequest(req);
            for (FileItem f : fileItem) {
                if (f.isFormField()) {
                    //文本
                    texthm.put(f.getFieldName(), EmojiUtil.emojiToString(f.getString("UTF-8")));
                }else {
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

        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        texthm.put("queid", Integer.parseInt(queid));
        texthm.put("userid", ub.getUserid());
        int result = quebz.updateQuestionById(texthm, Integer.parseInt(queid));
        if (result!=0){
            resp.sendRedirect("visitOther.do");
        }
    }
}
