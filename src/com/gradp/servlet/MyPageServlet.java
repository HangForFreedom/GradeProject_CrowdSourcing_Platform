package com.gradp.servlet;

import com.gradp.bean.AnswerBean;
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
import java.util.Set;

/**
 * <p>摘要：</p>
 * <p>备注：</p>
 */
public class MyPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");
        UserBiz ubz = new UserBiz();
        AnswerBiz ansbz = new AnswerBiz();
        QuestionBiz quebz = new QuestionBiz();

        //提出问题数量
        int quesum = ubz.queryQuestionById(ub.getUserid());
        //回答数量
        int anssum = ubz.queryAnswerById(ub.getUserid());

        req.setAttribute("quesum",quesum);
        req.setAttribute("anssum", anssum);

        /* 分页功能 */
        // 每页6条记录
        int pageSize = 6;
        //todo 修改后面的参数
        String anPage = req.getParameter("anPage");
        if (anPage == null || anPage == ""){
            anPage = "1";
        }
        int ansPage = Integer.parseInt(anPage);

        req.setAttribute("ansPage", ansPage);

        int ansTotalPage = ansbz.userToAnsTotalPages(pageSize, ub.getUserid());

        req.setAttribute("ansTotalPage", ansTotalPage);

        List<AnswerBean> ansbs = ansbz.queryAnswerAndQueByUserid(ansPage, pageSize, ub.getUserid());
        int ansSum = ansbs.size();

        req.setAttribute("ansSum",ansSum);

        List<Integer> agreedAnsList = ubz.queryAgreedAnsList(ub.getUserid());
        List<Integer> disagreedAnsList = ubz.queryDisagreedAnsList(ub.getUserid());
        String agids = "";
        for (Integer integer : agreedAnsList) {
            agids += integer + ",";
        }
        String disagIds = "";
        for (Integer integer : disagreedAnsList) {
            disagIds += integer + ",";
        }

        req.setAttribute("agreedAnsList", agids);
        req.setAttribute("disagreedAnsList", disagIds);
        req.setAttribute("solve", "采纳失败");

        for (AnswerBean a : ansbs){
            int ansid = a.getAnsid();
            int agnum = quebz.queryAgreeById(ansid);
            a.setAgnum(agnum);
            int disagnum = quebz.queryDisagreeById(ansid);
            a.setDisagnum(disagnum);
            String solveFlag = quebz.queryAnsSolveById(ansid);
            if (Integer.parseInt(solveFlag) != 0){
                req.setAttribute("solve", "已采纳");
                req.setAttribute("solveansid", ansid);
            }
        }

        req.setAttribute("ansbs", ansbs);

        /*  重复的我的问题  **/
        String quePage = req.getParameter("quePage");
        if (quePage == null || quePage == ""){
            quePage = "1";
        }
        int quesPage = Integer.parseInt(quePage);
        int queTotalPage = quebz.queTotalPagesById(pageSize, ub.getUserid());

        req.setAttribute("quesPage", quesPage);
        req.setAttribute("queTotalPage", queTotalPage);

        List<QuestionBean> quebs = quebz.queryQustionByUsername(quesPage, pageSize, ub.getUsername());
        int queSum = quebs.size();
        for (QuestionBean q : quebs){
            int queid = q.getQueid();
            int anssum2que = quebz.queryAnswerSumById(queid);
            q.setAnssum2que(anssum2que);List<Integer> answerAnsList = ubz.queryAswerAnsList(ub.getUserid(), queid);
            String answerIds = "";
            for (Integer integer : answerAnsList){
                answerIds += integer + ",";
            }
            req.setAttribute("answerAnsList", answerIds);
        }

        req.setAttribute("quebs", quebs);
        req.setAttribute("queSum",queSum);

        req.getRequestDispatcher("myPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        // doGet(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");
        String ansid = req.getParameter("ansid");

        AnswerBiz ansbz = new AnswerBiz();

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

        texthm.put("ansid", Integer.parseInt(ansid));
        int result = ansbz.updateAnswerById(texthm, Integer.parseInt(ansid));
        if (result!=0){
            resp.sendRedirect("myPage.do");
        }
    }
}