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
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>摘要：</p>
 * <p>备注：</p>
 */
public class VisitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");

        String queid = req.getParameter("queid");
        if(queid != null)
        {
            session.setAttribute("queid", queid);
        }
        else
        {
            queid = (String) session.getAttribute("queid");
        }

        QuestionBiz quebz = new QuestionBiz();
        // QuestionBean queb = quebz.querySingleQuestion(Integer.parseInt(quebId));
        List<QuestionBean> quebs = quebz.querySingleQuestion(Integer.parseInt(queid));
        req.setAttribute("quebs", quebs);

        //检查该问题是否解决
        String queSolveFlag = quebz.queryQueSolvedById(Integer.parseInt(queid));
        if (Integer.parseInt(queSolveFlag) != 0){
            req.setAttribute("queSolved", "已解决");
        }

        UserBiz ubz = new UserBiz();

        //提出问题数量
        int quesum = ubz.queryQuestionById(ub.getUserid());
        //回答数量
        int anssum = ubz.queryAnswerById(ub.getUserid());

        //查询根据queid查询所有回答
        List<AnswerBean> ansbs = quebz.queryAnswerById(Integer.parseInt(queid));

        UserBiz userbz = new UserBiz();
        List<Integer> agreedAnsList = userbz.queryAgreedAnsList(ub.getUserid());
        List<Integer> disagreedAnsList = userbz.queryDisagreedAnsList(ub.getUserid());
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
        for (AnswerBean an : ansbs){
            Integer ansid = an.getAnsid();
            // req.setAttribute("ansid", ansid);

            //查询每条回答点赞数量
            int agnum = quebz.queryAgreeById(ansid);
            an.setAgnum(agnum);
            // req.setAttribute(ansid.toString(), agnum);
            //查询不赞同数量
            int disagnum = quebz.queryDisagreeById(ansid);
            an.setDisagnum(disagnum);
            //查询是否采纳
            String solveFlag = quebz.queryAnsSolveById(ansid);
            // System.out.println(solveFlag);
            if (Integer.parseInt(solveFlag) != 0){
                req.setAttribute("solve", "已采纳");
                req.setAttribute("solveansid", ansid);
            }

        }
        //查询每条问题回答数量
        int anssum2que = quebz.queryAnswerSumById(Integer.parseInt(queid));

        req.setAttribute("quesum", quesum);
        req.setAttribute("anssum", anssum);
        req.setAttribute("ansbs", ansbs);
        req.setAttribute("anssum2que", anssum2que);

        List<Integer> queidList = quebz.queryQueidListByUserid(ub.getUserid());
        boolean status = queidList.contains(Integer.parseInt(queid));
        if (status){
            req.getRequestDispatcher("visitMy.jsp").forward(req, resp);
        }else {
            req.getRequestDispatcher("visitOther.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // doGet(req, resp);

        HttpSession session = req.getSession();
        String queid = (String) session.getAttribute("queid");
        UserBean ub = (UserBean) session.getAttribute("ub");

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

        texthm.put("queid", Integer.parseInt(queid));
        texthm.put("userid", ub.getUserid());
        int result = ansbz.answerToQuestion(texthm);
        if (result!=0){
            resp.sendRedirect("visitOther.do");
        }
    }
}
