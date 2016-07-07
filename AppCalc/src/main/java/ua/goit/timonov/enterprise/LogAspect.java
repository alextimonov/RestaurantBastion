package ua.goit.timonov.enterprise;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Alex on 07.07.2016.
 */
@Aspect
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(AppCalc.class);

   /* @Around("execution ( * Calc.doCalc(..) ) ")
    public Object onCalcExecute(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("Method " + pjp.getSignature().getName() + " before execute");
        System.out.println("Calc before");
        Object result = pjp.proceed();
        logger.info("Method " + pjp.getSignature().getName() + " after execute");
        System.out.println("Calc after");
        return result;
    }
*/
    @Around("execution ( * CalcNumbers.doCalc(..) ) ")
    public Object onCalcNumbersExecute(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("Method " + pjp.getSignature().getName() + " before execute");
        System.out.println("Calc before");
        Object result = pjp.proceed();
        logger.info("Method " + pjp.getSignature().getName() + " after execute");
        System.out.println("Calc after");
        return result;
    }

}
