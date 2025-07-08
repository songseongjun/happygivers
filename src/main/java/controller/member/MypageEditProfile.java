package controller.member;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSession;
import mapper.MemberMapper;
import domain.Member;
import util.MybatisUtil;


@WebServlet("/mypage/editprofile")
@MultipartConfig(
    maxFileSize = 1024 * 1024 * 5,
    fileSizeThreshold = 1024 * 1024
)
public class MypageEditProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/member/mypage/editprofile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 Part filePart = req.getPart("profileImage");
    	    String fileName = filePart.getSubmittedFileName();

    	    if (fileName != null && !fileName.isEmpty()) {
    	        String uploadPath = req.getServletContext().getRealPath("/upload");
    	        File uploadDir = new File(uploadPath);
    	        if (!uploadDir.exists()) uploadDir.mkdirs();
    	        
    	        		//중복되지않는파일만들기위해
    	        String uuid = UUID.randomUUID().toString();
    	        String saveName = uuid + "_" + fileName;
    	        filePart.write(uploadPath + File.separator + saveName);

    	        String webPath = req.getContextPath() + "/upload/" + saveName;

    	        HttpSession session = req.getSession();
    	        Member member = (Member) session.getAttribute("member");

    	        if (member != null) {
    	            member.setProfile(webPath);  // 세팅

    	            SqlSession sqlSession = MybatisUtil.getSqlSession();
    	            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
    	            mapper.updateProfileImage(member);
    	            sqlSession.commit();
    	            sqlSession.close();

    	            session.setAttribute("member", member); // 세션 갱신
    	        }
    	    }

    	    resp.sendRedirect(req.getContextPath() + "/mypage/editprofile");
    	}
}