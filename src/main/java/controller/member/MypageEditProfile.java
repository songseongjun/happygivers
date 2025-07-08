package controller.member;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import domain.Member;

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
              //경로변환 (webapp/upload 폴더)
              String realPath = req.getServletContext().getRealPath("/upload");
              File uploadDir = new File(realPath);
              if (!uploadDir.exists()) uploadDir.mkdirs();

              String saveName = UUID.randomUUID() + "_" + fileName;
              filePart.write(realPath + File.separator + saveName);

              //웹 브라우저에서 접근 가능한 경로
              String webPath = req.getContextPath() + "/upload/" + saveName;

              HttpSession session = req.getSession();
              Member member = (Member) session.getAttribute("member");
              if (member != null) {
                  member.setProfile(webPath);
                  session.setAttribute("member", member);
              }
          }

          resp.sendRedirect(req.getContextPath() + "/mypage/editprofile");
      }
  }