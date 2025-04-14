package org.mik.starterhomeworkaspect.aspect;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.mik.starterhomeworkaspect.aspect.exception.LoggingAspectException;
import org.mik.starterhomeworkaspect.properties.LoggingAspectProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;


import java.util.Arrays;
import java.util.List;

@Aspect
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private Level logLevel;

    public LoggingAspect(LoggingAspectProperties loggingAspectProperties) {
        this.logLevel = loggingAspectProperties.getLevel();
    }

    @Before(value = "@annotation(org.mik.starterhomeworkaspect.aspect.annotation.LoggingBeforeTrackingExecution)")
    public void loggingBeforeTracking(JoinPoint joinPoint) {
        String nameMethod = joinPoint.getSignature().getName();
        List<Object> argsMethod = Arrays.asList(joinPoint.getArgs());
        logger.atLevel(logLevel).log(
                "Вызов метода: {} Параметры метода: {}",
                nameMethod,argsMethod
        );
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
            logger.atLevel(logLevel).log(
                    "Выполнение метода: {} Параметры метода: {} Время выполнения метода: {} мс",
                    nameMethod,argsMethod,timeProceeded);
            return proceeded;
        } catch (Throwable throwable) {
            String nameMethod = joinPoint.getSignature().getName();
            List<Object> argsMethod = Arrays.asList(joinPoint.getArgs());
            List<StackTraceElement> stackTraceElementsMethod = Arrays.asList(throwable.getStackTrace());
            logger.atLevel(logLevel).log(
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
        logger.atLevel(logLevel).log(
                "Ошибка в работе метода: {} Параметры метода: {} Код ошибки: {}",
                nameMethod,argsMethod,stackTraceElementsMethod);
    }


    @AfterReturning(pointcut = "@annotation(org.mik.starterhomeworkaspect.aspect.annotation.LoggingReturnTrackingExecution)",
                    returning = "resultMethod")
    public void loggingReturningTracking(JoinPoint joinPoint,Object resultMethod) {
        String nameMethod = joinPoint.getSignature().getName();
        List<Object> argsMethod = Arrays.asList(joinPoint.getArgs());
        logger.atLevel(logLevel).log(
                "Выполнение метода: {} Параметры метода: {} Результат выполнение метода: {}",
                nameMethod,argsMethod,resultMethod);
    }


}
