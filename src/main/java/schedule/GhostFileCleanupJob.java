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

@Slf4j
public class GhostFileCleanupJob implements Job{

	@SuppressWarnings("null")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Long date = new Date().getTime() - 1000 * 60 * 60 * 24;
		SimpleDateFormat formatter = new SimpleDateFormat("/yyyy/MM/dd");
		String datefolder = formatter.format(date);
		
		File file = new File(UploadFile.UPLOAD_PATH, datefolder);
		if(!file.exists() && !file.isDirectory()) {
			return;
		}


		try (SqlSession session = MybatisUtil.getSqlSession()){
			List<File> fsListFiles = new ArrayList<File>(Arrays.asList(file.listFiles()));
			
			List<Attach> attachs = new ArrayList<Attach>(session.getMapper(AttachMapper.class).selectYesterdayList());
			
			List<Attach> linked = attachs.stream().filter(a -> a.getBno() != null || a.getMno() != null || a.getViewbno() != null).collect(Collectors.toList());
			
			List<File> protectedFiles = linked.stream().map(Attach::toFile).toList();
			
			
			
			
			fsListFiles.removeAll(protectedFiles);
			log.info("{}", fsListFiles);
			fsListFiles.forEach(f -> f.delete());
			session.close();
			
			 int deletedCount = 0;
		        long totalSize = 0;

		        for (File f : fsListFiles) {
		            if (f.delete()) {
		                deletedCount++;
		                totalSize += f.length();
		                log.info("[DELETE] {}", f.getAbsolutePath());
		            } else {
		                log.warn("[FAIL] 삭제 실패: {}", f.getAbsolutePath());
		            }
		        }

		        // ✅ 최종 통계 출력
		        double mb = totalSize / 1024.0 / 1024.0;
		        log.info("[정리 완료] 삭제한 파일 수: {}개, 총 용량: {} MB", deletedCount, mb);
			
			
			
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws JobExecutionException {
		new GhostFileCleanupJob().execute(null);
	}
}
