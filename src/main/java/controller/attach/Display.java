package controller.attach;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/display")
public class Display extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // 쿼리 스트링 해석으로 Attach 객체 생성
		// uuid, origin, path

		String uuid = req.getParameter("uuid");
		String path = req.getParameter("path");
		
		log.info("{} :: {}", uuid, path);


		if (uuid == null || path == null) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}

		// S3 경로 구성
		String s3Url = "https://happygivers-bucket.s3.ap-northeast-2.amazonaws.com/upload/" + path + "/" + uuid;

		// 브라우저를 S3 URL로 리다이렉트
		resp.sendRedirect(s3Url);
	}
	
	
}
