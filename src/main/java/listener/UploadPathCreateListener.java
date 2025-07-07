package listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class UploadPathCreateListener implements ServletContextListener{
	@Override
    public void contextInitialized(ServletContextEvent sce) {
        String[] dirs = {
            "c:/upload/tmp",
            "c:/upload/files"
        };

        for (String path : dirs) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
                System.out.println("디렉토리 생성: " + path);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 필요 없으면 비워두면 됨
    }
}
