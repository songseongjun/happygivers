package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import schedule.GhostFileCleanupJob;

@WebListener
public class SchedulerListener implements ServletContextListener{
	private Scheduler scheduler;


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		 try {
	            System.out.println("서버 실행됨 > GhostFileCleanupJob 실행");

	            // 1. 서버 시작 시 GhostFileCleanupJob 즉시 1회 실행
//	            new GhostFileCleanupJob().execute(null);

	            // 2. Quartz 스케줄러 생성
	            scheduler = StdSchedulerFactory.getDefaultScheduler();

	            // 3. 매일 새벽 2시에 실행되도록 Job + Trigger 설정
	            JobDetail job = JobBuilder.newJob(GhostFileCleanupJob.class)
	                    .withIdentity("ghostJob", "ghostGroup")
	                    .build();

	            Trigger trigger = TriggerBuilder.newTrigger()
	                    .withIdentity("ghostTrigger", "ghostGroup")
	                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 2 * * ?")) // 매일 2시
	                    .build();

	            // 4. 등록하고 시작
	            scheduler.scheduleJob(job, trigger);
	            scheduler.start();

	            System.out.println("GhostFileCleanupJob 스케줄러 등록됨: 매일 새벽 2시 고스트 파일 정리");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		 try {
	            if (scheduler != null && !scheduler.isShutdown()) {
	                scheduler.shutdown();
	                System.out.println("스케줄러 정상 종료");
	            }
	        } catch (SchedulerException e) {
	            e.printStackTrace();
	        }
	}
}
