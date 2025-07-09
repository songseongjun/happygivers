package schedule;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import controller.attach.UploadFile;
import domain.Attach;
import lombok.extern.slf4j.Slf4j;
import mapper.AttachMapper;
import util.MybatisUtil;
import util.S3Util;

@Slf4j
public class GhostFileCleanupJob implements Job{

	@SuppressWarnings("null")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Long date = new Date().getTime() - 1000 * 60 * 60 * 24;
		SimpleDateFormat formatter = new SimpleDateFormat("/yyyy/MM/dd");
		String datefolder = formatter.format(date);
		String s3Prefix = "upload" + datefolder + "/";


		try (SqlSession session = MybatisUtil.getSqlSession()){

			List<Attach> attachs = new ArrayList<Attach>(session.getMapper(AttachMapper.class).selectYesterdayList());


			// 3. DB에 등록된 S3 key 리스트 만들기
			List<String> dbKeyList = attachs.stream()
			    .map(Attach::getS3Key)
			    .toList();

			List<String> s3KeyList = S3Util.listObjects(s3Prefix);

			s3KeyList.removeAll(dbKeyList);
			
			int deletedCount = S3Util.removeAll(s3KeyList);
			
			
			session.close();
			
			 
			log.info("[정리 완료] 서버에서 삭제한 파일 수: {}개", deletedCount);

	        
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) throws JobExecutionException {
		new GhostFileCleanupJob().execute(null);
	}
	
	
	
}
