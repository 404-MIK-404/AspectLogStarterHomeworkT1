package org.mik.starterhomeworkaspect.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.mik.starterhomeworkaspect.aspect.exception.LoggingAspectException;
import org.mik.starterhomeworkaspect.logging.LoggingStrategy;
import org.mik.starterhomeworkaspect.logging.factory.LoggingStrategyFactory;
import org.mik.starterhomeworkaspect.properties.LoggingAspectProperties;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final LoggingStrategy loggingStrategy;

    public LoggingAspect(LoggingAspectProperties loggingAspectProperties, LoggingStrategyFactory loggingStrategyFactory) {
        this.loggingStrategy = loggingStrategyFactory.getLoggingStrategy(loggingAspectProperties.getLevel());
    }


    @Before(value = "@annotation(org.mik.starterhomeworkaspect.aspect.annotation.LoggingBeforeTrackingExecution)")
    public void loggingBeforeTracking(JoinPoint joinPoint) {
        loggingStrategy.log("Before !");
    }

    @Around(value = "@annotation(org.mik.starterhomeworkaspect.aspect.annotation.LoggingAroundTrackingExecution)")
    public Object loggingAroundTracking(ProceedingJoinPoint joinPoint) {
        try {
            long startProceeded = System.currentTimeMillis();
            Object proceeded;
            proceeded = joinPoint.proceed();
            long endProceeded = System.currentTimeMillis();
            long timeProceeded = endProceeded - startProceeded;
            loggingStrategy.log("Around !");
            return proceeded;
        } catch (Throwable throwable) {
            throw new LoggingAspectException("");
        }
    }

    @AfterThrowing(pointcut = "@annotation(org.mik.starterhomeworkaspect.aspect.annotation.LoggingThrowTrackingExecution)",
                   throwing = "throwable")
    public void loggingThrowingTracking(JoinPoint joinPoint, Throwable throwable) {
        loggingStrategy.log("AfterThrowing !");
    }


    @AfterReturning(pointcut = "@annotation(org.mik.starterhomeworkaspect.aspect.annotation.LoggingReturnTrackingExecution)",
                    returning = "data")
    public void loggingReturningTracking(JoinPoint joinPoint,Object data) {
        loggingStrategy.log("AfterReturning !");
    }


}
