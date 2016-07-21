package ua.goit.timonov.enterprise;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * Created by Alex on 07.07.2016.
 */
@Aspect
@EnableAspectJAutoProxy
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(AppCalc.class);

//    @Bean
//    public FactoryNumberExpression factoryNumberExpression() {
//        FactoryNumberExpression factoryExpression = new FactoryNumberExpression();
//        return factoryExpression;
//    }

//    @Bean
//    public ParserStringToStringExpression parserStringToStringExpression() {
//        ParserStringToStringExpression stringParser = new ParserStringToStringExpression();
//        return stringParser;
//    }

    @Pointcut("execution ( * ua.goit.timonov.enterprise.Calc.doCalc(..) ) ")
    public Object onCalcExecute() {
        return null;
    }

    @Around("onCalcExecute()")
    public Object onCalcExecute(ProceedingJoinPoint pjp) throws Throwable {
        return logAndProceed(pjp);
    }

    @Around("execution( * ua.goit.timonov.enterprise.StringParser.parse(..) )")
    public Object onParserStringToStringExpressionExecution(ProceedingJoinPoint pjp) throws Throwable {
        return logAndProceed(pjp);
    }

    @Around("execution ( * ua.goit.timonov.enterprise.FactoryExpression.makeExpression(..) ) ")
    public Object onMakeExpression(ProceedingJoinPoint pjp) throws Throwable {
        return logAndProceed(pjp);
    }

    @Around("execution( * ua.goit.timonov.enterprise.Expression.*(..) )")
    public Object onExpressionExecute(ProceedingJoinPoint pjp) throws Throwable {
        return logAndProceed(pjp);
    }

//    @Around("execution( * ua.goit.timonov.enterprise.ExpressionIntegerPlusMinus.*(..) )")
//    public Object onExpressionIPMExecute(ProceedingJoinPoint pjp) throws Throwable {
//        return logAndProceed(pjp);
//    }

    @Around("execution( * ua.goit.timonov.enterprise.ExpressionProvider.getExpression(..) )")
    public Object onGetExpressionExecute(ProceedingJoinPoint pjp) throws Throwable {
        return logAndProceed(pjp);
    }



//    @Around("execution(public * *(..))")
//    public void onPublicExecution(ProceedingJoinPoint pjp) throws Throwable {
//        logger.info("Class {}, PUBLIC method {} before executing", pjp.getTarget().getClass().getSimpleName(), pjp.getSignature().getName());
//        pjp.proceed();
//        logger.info("Class {}, PUBLIC method {} before executing", pjp.getTarget().getClass().getSimpleName(), pjp.getSignature().getName());
//    }

    private Object logAndProceed(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("Class {}, method {} before executing", pjp.getTarget().getClass().getSimpleName(), pjp.getSignature().getName());
        Object result = pjp.proceed();
        logger.info("Class {}, method {} after executing", pjp.getTarget().getClass().getSimpleName(), pjp.getSignature().getName());
        return result;
    }

}
