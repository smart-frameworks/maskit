/*
 * Copyright (c) 2010 BarclaycardUS All rights reserved.
 * 
 * This software is the confidential and proprietary information of
 * BarclaycardUS. ("Confidential Information").
 * 
 * File: PerformanceTrackingAspect.java Created: April 11, 2013
 */
package com.smartframeworks.core.aspecting;

import ch.qos.logback.classic.LoggerContext;
import com.smartframeworks.core.data.RequestStopWatch;
import com.smartframeworks.core.logging.LoggingConstants;
import com.smartframeworks.core.logging.annotations.LogMethodPerformance;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * This class extends the base method name aspect which intercepts method calls.
 *
 * @author pavan mummareddi
 */
@Aspect
public class PerformanceTrackingAspect extends LoggingAspect
{

   @Around(value = "logMethodPerformanceCall(logMethodPerformance)", argNames = "joinPoint,logMethodPerformance")
   public Object aroundMethodExecution(ProceedingJoinPoint joinPoint,
      LogMethodPerformance logMethodPerformance) throws Throwable
   {

      Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getClass());

      if (logMethodPerformance.enabled() && isPerformanceLogEnabled())
      {
         logger = LoggerFactory.getLogger(PERFORMANCE_LOGGER);
      }
      RequestStopWatch requestStopWatch = new RequestStopWatch();
      try
      {
         requestStopWatch.start();
         Object returnValue = this.proceedToMethodExecution(joinPoint);
         requestStopWatch.stop();
         this.logElapsedTime(joinPoint, logMethodPerformance, requestStopWatch, logger);
         return returnValue;
      }
      catch (Throwable e)
      {
         this.logElapsedTimeOnException(joinPoint, logMethodPerformance, requestStopWatch, logger);
         throw e;
      }
   }
   private static final String PERFORMANCE_LOGGER = "performance.logger";

   private boolean isPerformanceLogEnabled()
   {
      LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
      return loggerContext.exists(PERFORMANCE_LOGGER) != null;
   }

   private Object proceedToMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable
   {
      Object[] args = joinPoint.getArgs();
      Object returnValue = null;
      if (args == null)
      {
         returnValue = joinPoint.proceed();
      }
      else
      {
         returnValue = joinPoint.proceed(args);
      }
      return returnValue;
   }

   private void addExecutionInfo(ProceedingJoinPoint joinPoint)
   {
      if (!isPerformanceLogEnabled())
      {
         MDC.put(LoggingConstants.METHOD_NAME.name(), joinPoint.getSignature().getName());
      }
   }

   private void logElapsedTime(ProceedingJoinPoint joinPoint, LogMethodPerformance logMethodPerformance,
      RequestStopWatch requestStopWatch, Logger logger)
   {
      if (logMethodPerformance.enabled())
      {
         /* Variables needed by logback* */
         MDC.put(LoggingConstants.TARGET_CLASS_NAME.name(), joinPoint.getSignature().getDeclaringTypeName());
         MDC.put(LoggingConstants.TARGET_METHOD_NAME.name(), joinPoint.getSignature().getName());
         /* Variables needed by logback* */
         StringBuilder logMsgBuilder = new StringBuilder();
         addExecutionInfo(joinPoint);

         String prefix = getPrefix(logMethodPerformance);
         if (!prefix.isEmpty())
         {
            logMsgBuilder.append(prefix);
         }

         logMsgBuilder.append(" Method call successfull,").append(requestStopWatch.getElapsedTime());

         String suffix = getSuffix(logMethodPerformance);

         if (!suffix.isEmpty())
         {
            logMsgBuilder.append(suffix);
         }

         logger.info(logMsgBuilder.toString());
      }
   }

   private void logElapsedTimeOnException(ProceedingJoinPoint joinPoint, LogMethodPerformance logMethodPerformance,
      RequestStopWatch requestStopWatch, Logger logger)
   {
      if (logMethodPerformance.enabled())
      {
         /* Variables needed by logback* */
         MDC.put(LoggingConstants.TARGET_CLASS_NAME.name(), joinPoint.getSignature().getDeclaringTypeName());
         MDC.put(LoggingConstants.TARGET_METHOD_NAME.name(), joinPoint.getSignature().getName());
         /* Variables needed by logback* */
         StringBuilder logMsgBuilder = new StringBuilder();
         addExecutionInfo(joinPoint);

         String prefix = getPrefix(logMethodPerformance);
         if (!prefix.isEmpty())
         {
            logMsgBuilder.append(prefix);
         }

         logMsgBuilder.append("Method call failed,").append(requestStopWatch.getElapsedTime());

         String suffix = getSuffix(logMethodPerformance);

         if (!suffix.isEmpty())
         {
            logMsgBuilder.append(suffix);
         }
         logger.info(logMsgBuilder.toString());
      }
   }

   private String getPrefix(LogMethodPerformance logMethodPerformance)
   {
      String prefix = logMethodPerformance.prefix();
      prefix = prefix == null ? "" : prefix;
      return prefix;
   }

   private String getSuffix(LogMethodPerformance logMethodPerformance)
   {
      String suffix = logMethodPerformance.suffix();
      suffix = suffix == null ? "" : suffix;
      return suffix;
   }

}
