package co.bibleit.springboot.examples.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyExampleLoggingAspect {

    @Before("co.bibleit.springboot.examples.aop.aspect.LuvAOPExpressions.forDAOPackageNoGetterSetter()")
    public void beforeAnyMethodInsideAPackage(){
        System.out.println("\n=========>>> LOGGING @Before advice on ANY!!! Method inside choosen package");
    }

}
