package cn.hncu.springmvc;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/19.
 * Time: 下午 3:44.
 * Explain:Web配置
 */
public class WebInitializer implements WebApplicationInitializer {
    //WebApplicationInitializer是spring提供用来配置Servlet3.0+配置的接口，从而实现了替代web.xml的位置。
    //实现此接口将会自动被SpringServletContainerInitializer（用来启动Servlet3.0容器）获取到

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MyMvcConfig.class);
        context.setServletContext(servletContext);//新建WebApplicationContext,注册配置类，并将其和当前servletContext关联

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",new DispatcherServlet(context));
        //注册Spring MVC的DispatcherServlet
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
