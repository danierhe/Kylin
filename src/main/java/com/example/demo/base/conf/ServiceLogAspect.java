package com.example.demo.base.conf;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName ServiceLogAspect
 * @Description 在方法调用时打印Service层日志
 * @Author qp
 * @Date 2019/2/27 14:27
 **/
@Aspect
@Component
@Order(0)
public class ServiceLogAspect  {
    private final static transient Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);
    //private final String POINT = "execution(public * *.*.*.*.service.*.*(..))";
    private final String POINT = "execution(public * com.example.demo.*.service.*.*(..))";

    @Pointcut(POINT)
    public void serviceLog() {
    }

    @Before(value = POINT)
    public void before(JoinPoint point) {
        LocalVariableTableParameterNameDiscoverer nameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        Signature signature = point.getSignature();
        Class type = signature.getDeclaringType();
        String methodName = signature.getName();
        Method[] method = type.getMethods();

        for (Method method1 : method) {
            if (method1.getName().equalsIgnoreCase(methodName)) {
                String[] names = nameDiscoverer.getParameterNames(method1);
                Object[] args = point.getArgs();
                if(names != null && args != null && names.length == args.length){
                    for (int i = 0; i < names.length; i++) {
                        log.info((methodName + " 参数:"+ names[i] + "----> " + args[i]));
                    }
                }
            }
        }
    }
}
