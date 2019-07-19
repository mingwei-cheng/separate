package cn.db.separate.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Cheng
 * @create 2019-07-08 10:44
 **/
@Aspect
@Component
public class DataSourceAop {
    @Pointcut("(execution(* cn.db.separate.service..*.select*(..)) " +
            "|| execution(* cn.db.separate.service..*.get*(..)))")
    public void readPointcut() {}

    @Pointcut("execution(* cn.db.separate.service..*.insert*(..)) " +
            "|| execution(* cn.db.separate.service..*.add*(..)) " +
            "|| execution(* cn.db.separate.service..*.update*(..)) " +
            "|| execution(* cn.db.separate.service..*.edit*(..)) " +
            "|| execution(* cn.db.separate.service..*.delete*(..)) " +
            "|| execution(* cn.db.separate.service..*.remove*(..))")
    public void writePointcut() {}

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}
