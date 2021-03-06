package co.bibleit.springboot.examples.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAOPExpressions {
    // execution((* any return typy)(on package).(any class).(any method))
    @Pointcut("execution(* co.bibleit.springboot.examples.aop.dao.*.*(..))")
    public void forDAOPackage(){}


    //@Before("execution(public void co.bibleit.springboot.examples.aop.dao.AccountDAO.addAccount())")
    //                              -- will only match on AccoutDAO addAccount()
    //@Before("execution(public void addAccount())")  -- will match on any method called addAccount()
    //@Before("execution(public void add*())")
    //@Before("execution(* add*())") -- will match on any return type that starts with add
//    @Before("execution(void add*())") // public is optional -- match on a method of return void
//    public void beforeAddAccountAdvice(){
//        System.out.println("\n=========>>> execting @Before advice on ANY!!! addAccount()");
//    }
//
//    @Before("execution(void add*(co.bibleit.springboot.examples.aop.Account, ..))")
//    public void beforeAddAccountWithArgsAdvice(){
//        System.out.println("\n=========>>> execting @Before advice on WITH ARGS addAccount()");
//    }


    // create pointcut for getters
    @Pointcut("execution(* co.bibleit.springboot.examples.aop.dao.*.get*(..))")
    public void getter(){

    }

    // create pointcut for setters
    @Pointcut("execution(* co.bibleit.springboot.examples.aop.dao.*.set*(..))")
    public void setter(){}


    // create pointcut: include package... exclude getter/setters
    @Pointcut("forDAOPackage() && !(getter() || setter())")
    public void forDAOPackageNoGetterSetter(){}
}
