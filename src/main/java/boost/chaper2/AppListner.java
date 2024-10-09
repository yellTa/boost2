package boost.chaper2;


import boost.chaper2.dao.TaskDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        TaskDao.closeConnection();
        TaskDao.cleanUpSQLDriver();
    }
}