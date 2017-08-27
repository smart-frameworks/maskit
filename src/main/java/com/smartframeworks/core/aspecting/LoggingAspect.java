/*
 * 
 * Copyright (c) 2011 BarclaycardUS All rights reserved.
 * 
 * This software is the confidential and proprietary information of
 * BarclaycardUS. ("Confidential Information").
 * 
 * File: LoggingAspect.java Created: Jul 22, 2011
 */
package com.smartframeworks.core.aspecting;

import com.smartframeworks.core.logging.annotations.LogMethodPerformance;
import org.aspectj.lang.annotation.Pointcut;

/**
 *
 * @author pavan mummareddi
 */
public abstract class LoggingAspect
{

   @Pointcut("(!within((com.smartframeworks.core.aspecting.PerformanceTrackingAspect))"
      + " && ((call(void org.slf4j.Logger.debug(..)))"
      + " || (call(void org.slf4j.Logger.error(..)))"
      + " || (call(void org.slf4j.Logger.info(..)))"
      + " || (call(void org.slf4j.Logger.trace(..)))"
      + " || (call(void org.slf4j.Logger.warn(..)))"
      + " || (call(void org.slf4j.Logger.fail(..)))))")
   public void slf4JCall()
   {
   }

   @Pointcut("within(com..*) && execution(* com..*(..)) && @annotation(logMethodPerformance)")
   public void logMethodPerformanceCall(
      LogMethodPerformance logMethodPerformance)
   {
   }

}
