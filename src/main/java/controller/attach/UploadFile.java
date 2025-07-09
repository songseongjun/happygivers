package controller.attach;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import domain.Attach;

import java.io.File;


import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import util.S3Util;

@WebServlet("/upload")
@MultipartConfig(
		maxRequestSize = 50 * 1024 * 1024,
		maxFileSize = 10 * 1024 * 1024,
		fileSizeThreshold = 10 * 1024 * 1024
)
@Slf4j
public class UploadFile extends HttpServlet{
	private static final String BUCKET_PATH = "https://happygivers-bucket.s3.ap-northeast-2.amazonaws.com/upload";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 업로드된 파일처리
		Collection<Part> parts = req.getParts();
		List<Attach> attachs = new ArrayList<Attach>();
		
		int odr = 0;
		for(Part part : parts) {
			if(part.getSize() == 0) {
				continue;
			}

			String origin = part.getSubmittedFileName();
			String contentType = part.getContentType();
			boolean image = contentType != null && contentType.startsWith("image");
		
			
			// image/png, image/jpg, image/gif, image/webp, image/bmp, image/jpeg
			
			// ext추출
			int idx = origin.lastIndexOf(".");
			String ext = "";
			if(idx >= 0) {
				// 확장자가 존재하는 경우
				ext = origin.substring(idx).toLowerCase();
			}
			
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + ext;

			String path = genPath();

			S3Util.upload(part, "upload/" + path + "/" + fileName);

			
			
			log.info("{} :: {} :: {}", origin, contentType, ext);
			attachs.add(Attach.builder().uuid(fileName).origin(origin).image(image).path(path).odr(odr++).build());
		}
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new Gson().toJson(attachs));
	}
	
	private String genPath() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}
}
