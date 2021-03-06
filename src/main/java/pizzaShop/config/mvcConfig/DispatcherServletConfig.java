package pizzaShop.config.mvcConfig;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.*;
import javax.servlet.ServletRegistration.Dynamic;

public class DispatcherServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.setInitParameter("spring.profiles.active", "prod");
    }
    @Override
    public void customizeRegistration(Dynamic registration) {
       registration.setMultipartConfig(new MultipartConfigElement("/tmp"));
//       registration.setMultipartConfig(new MultipartConfigElement("D:\\temp"));
    }

}
