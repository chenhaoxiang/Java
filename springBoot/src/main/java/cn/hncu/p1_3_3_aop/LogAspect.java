package cn.hncu.p1_3_3_aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/9.
 * Time: 上午 11:24.
 * Explain:切面
 */
@Aspect
//通过@Aspect注解声明这是一个切面
@Component
//通过@Component让此切面成为Spring容器管理的Bean
public class LogAspect {

    //注意这两个@Pointcut写法的区别！！！
    // 一个是拦截注解(写了@Action注解的方法都会被拦截)，一个是拦截类方法
    @Pointcut("@annotation(cn.hncu.p1_3_3_aop.Action)")//通过@PointCut注解声明切点
    //@Pointcut("execution(* cn.hncu.p1_3_3_aop.DemoAnnotationService..*(..))")
    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    public void annotatiomPointCut(){ }

    //拦截注解
    @After("annotatiomPointCut()")//通过@After注解声明一个建言，并使用@PointCut定义的切点
    public void after(JoinPoint joinPoint){
        MethodSignature signature =(MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println(action.name());//通过反射可获得注解上的属性，可以用来做日志记录等相关操作
    }

    @Before("execution(* cn.hncu.p1_3_3_aop.DemoMethodService.*(..))")
    //通过@Before注解声明一个建言，此建言直接使用拦截规则作为参数
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("方法规则式拦截:" + method.getName()+"  "+action.name());
    }


}
