package ua.goit.timonov.enterprise;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * Created by Alex on 07.07.2016.
 */
@Aspect
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(AppCalc.class);

    @Bean
    public FactoryNumberExpression factoryNumberExpression() {
        FactoryNumberExpression factoryExpression = new FactoryNumberExpression();
        return factoryExpression;
    }

    @Bean
    public ParserStringToStringExpression parserStringToStringExpression() {
        ParserStringToStringExpression stringParser = new ParserStringToStringExpression();
        return stringParser;
    }

    @Bean ExpressionIntegerPlusMinus expressionIntegerPlusMinus() {
        ExpressionIntegerPlusMinus expressionIntegerPlusMinus = new ExpressionIntegerPlusMinus();
        return expressionIntegerPlusMinus;
    }

    @Around("execution ( * CalcNumbers.doCalc(..) ) ")
    public Object onCalcExecute(ProceedingJoinPoint pjp) throws Throwable {
        return logAndProceed(pjp);
    }

    @Around("execution( * ParserStringToStringExpression.parse(..) )")
    public Object onParserStringToStringExpressionExecution(ProceedingJoinPoint pjp) throws Throwable {
        return logAndProceed(pjp);
    }

    @Around("execution ( * FactoryNumberExpression.makeExpression(..) ) ")
    public Object onMakeExpression(ProceedingJoinPoint pjp) throws Throwable {
        return logAndProceed(pjp);
    }

    @Around("execution( * Expression.*(..) )")
    public Object onExpressionExecute(ProceedingJoinPoint pjp) throws Throwable {
        return logAndProceed(pjp);
    }

    @Around("execution( * ExpressionIntegerPlusMinus.*(..) )")
    public Object onExpressionIPMExecute(ProceedingJoinPoint pjp) throws Throwable {
        return logAndProceed(pjp);
    }

    private Object logAndProceed(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("Class " + pjp.getTarget().getClass().getSimpleName() + ", method " + pjp.getSignature().getName() + " before executing");
        Object result = pjp.proceed();
        logger.info("Class " + pjp.getTarget().getClass().getSimpleName() + ", method " + pjp.getSignature().getName() + " after executing");
        return result;
    }

}
