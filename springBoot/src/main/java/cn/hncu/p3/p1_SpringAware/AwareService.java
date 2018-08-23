package cn.hncu.p3.p1_SpringAware;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/16.
 * Time: 下午 6:37.
 * Explain:Spring Aware演示Bean
 */
@Service
public class AwareService implements BeanNameAware,ResourceLoaderAware {
    //实现BeanNameAware,ResourceLoaderAware接口，获得Bean名称和资源加载的服务

    private String beanName;
    private ResourceLoader loader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {//实现ResourceLoaderAware需要重写setResourceLoader方法
        this.loader = resourceLoader;
    }

    @Override
    public void setBeanName(String beanName) {//实现BeanNameAware需要重写setBeanName方法
        this.beanName = beanName;
    }

    public void outputResult(){
        System.out.println("Bean的名称为:"+beanName);
        Resource resource = loader.getResource("cn/hncu/p3/p1_SpringAware/test.txt");

        try {
            System.out.println("ResourceLoader加载的文件内容为: "+ IOUtils.toString(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
