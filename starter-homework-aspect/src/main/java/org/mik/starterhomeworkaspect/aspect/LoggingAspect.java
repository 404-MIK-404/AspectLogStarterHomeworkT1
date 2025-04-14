package org.mik.starterhomeworkaspect.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.mik.starterhomeworkaspect.aspect.exception.LoggingAspectException;
import org.mik.starterhomeworkaspect.logging.LoggingStrategy;
import org.mik.starterhomeworkaspect.logging.factory.LoggingStrategyFactory;
import org.mik.starterhomeworkaspect.properties.LoggingAspectProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class LoggingAspect {

    private final LoggingStrategy loggingStrategy;

    public LoggingAspect(LoggingAspectProperties loggingAspectProperties, LoggingStrategyFactory loggingStrategyFactory) {
        this.loggingStrategy = loggingStrategyFactory.getLoggingStrategy(loggingAspectProperties.getLevel());
    }


    @Before(value = "@annotation(org.mik.starterhomeworkaspect.aspect.annotation.LoggingBeforeTrackingExecution)")
    public void loggingBeforeTracking(JoinPoint joinPoint) {
        String nameMethod = joinPoint.getSignature().getName();
        List<Object> argsMethod = Arrays.asList(joinPoint.getArgs());
        loggingStrategy.log(
                "Вызов метода: {} Параметры метода: {}",
                nameMethod,argsMethod);
    }

    @Around(value = "@annotation(org.mik.starterhomeworkaspect.aspect.annotation.LoggingAroundTrackingExecution)")
    public Object loggingAroundTracking(ProceedingJoinPoint joinPoint) {
        try {
            String nameMethod = joinPoint.getSignature().getName();
            List<Object> argsMethod = Arrays.asList(joinPoint.getArgs());
            long startProceeded = System.currentTimeMillis();
            Object proceeded;
            proceeded = joinPoint.proceed();
            long endProceeded = System.currentTimeMillis();
            long timeProceeded = endProceeded - startProceeded;
            loggingStrategy.log(
                    "Выполнение метода: {} Параметры метода: {} Время выполнения метода: {} мс",
                    nameMethod,argsMethod,timeProceeded);
            return proceeded;
        } catch (Throwable throwable) {
            String nameMethod = joinPoint.getSignature().getName();
            List<Object> argsMethod = Arrays.asList(joinPoint.getArgs());
            List<StackTraceElement> stackTraceElementsMethod = Arrays.asList(throwable.getStackTrace());
            loggingStrategy.log(
                    "Произошла ошибка в выполнение метода: {} Параметры метода: {} StackTrace ошибки: {}",
                    nameMethod,argsMethod,stackTraceElementsMethod);
            throw new LoggingAspectException("Ошибка в аспект классе.\n Код ошибки: " + throwable.getMessage());
        }
    }

    @AfterThrowing(pointcut = "@annotation(org.mik.starterhomeworkaspect.aspect.annotation.LoggingThrowTrackingExecution)",
                   throwing = "throwable")
    public void loggingThrowingTracking(JoinPoint joinPoint, Throwable throwable) {
        String nameMethod = joinPoint.getSignature().getName();
        List<Object> argsMethod = Arrays.asList(joinPoint.getArgs());
        List<StackTraceElement> stackTraceElementsMethod = Arrays.asList(throwable.getStackTrace());
        loggingStrategy.log(
                "Ошибка в работе метода: {} Параметры метода: {} Код ошибки: {}",
                nameMethod,argsMethod,stackTraceElementsMethod);
    }


    @AfterReturning(pointcut = "@annotation(org.mik.starterhomeworkaspect.aspect.annotation.LoggingReturnTrackingExecution)",
                    returning = "resultMethod")
    public void loggingReturningTracking(JoinPoint joinPoint,Object resultMethod) {
        String nameMethod = joinPoint.getSignature().getName();
        List<Object> argsMethod = Arrays.asList(joinPoint.getArgs());
        loggingStrategy.log(
                "Выполнение метода: {} Параметры метода: {} Результат выполнение метода: {}",
                nameMethod,argsMethod,resultMethod);
    }


}
