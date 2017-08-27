/*
 * Copyright (c) 2010 BarclaycardUS
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of BarclaycardUS.
 * ("Confidential Information").
 * 
 *  File: MethodNameAspect.java
 *  Created: Aug 31, 2010
 * 
 */
package com.smartframeworks.core.aspecting;

import com.smartframeworks.core.logging.LoggingConstants;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.EnclosingStaticPart;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.slf4j.MDC;

/**
 * This class extends the base method name aspect which intercepts method calls.
 *
 * @author pavan mummareddi
 */
@Aspect
public class MethodNameAspect extends LoggingAspect
{

   @Before("slf4JCall()")
   public void before(EnclosingStaticPart esp)
   {
      MDC.put(LoggingConstants.METHOD_NAME.name(), esp.getSignature().getName());
   }

   @After("slf4JCall()")
   public void after(JoinPoint jp)
   {
      MDC.remove(LoggingConstants.METHOD_NAME.name());
   }
}
