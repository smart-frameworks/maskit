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

import com.smartframeworks.core.util.Mask;

import java.lang.reflect.Array;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * This class extends the base method name aspect which intercepts method calls.
 *
 * @author pavan mummareddi
 */
@Aspect
public class MaskAspect extends LoggingAspect
{

   @Around("slf4JCall()")
   public Object MaskSensitiveData(ProceedingJoinPoint pjp) throws Throwable
   {
      // Get the arguments.
      Object[] args = pjp.getArgs();

      // Check to see if the arguments are valid.
      if (args == null)
      {
         return pjp.proceed();
      }

      // Loop through the arguments and check for any sensitive data.
      Object arg;
      for (int i = 0; i < args.length; i++)
      {
         // Get the current argument.
         arg = args[i];

         // Ignore null or exception objects
         if (arg == null || arg instanceof Throwable)
         {
            continue;
         }

         // If arg is an array then apply mask to each element
         // Assumption is that the array elements are not arrays them self
         if (arg.getClass().isArray())
         {
            for (int j = 0; j < Array.getLength(arg); j++)
            {
               Object arrayElement = Array.get(arg, j);

               // ignore null elements
               if (arrayElement == null)
               {
                  continue;
               }

               // if element is an array then don't change it
               if (arrayElement.getClass().isArray())
               {
                  continue;
               }

               // Replace the element with the modified message.
               Array.set(arg, j, applyMask(arrayElement));
            }

         }
         else
         {
            // Replace the arg with the modified message.
            args[i] = applyMask(arg);
         }
      }

      return pjp.proceed(args);
   }

   /**
    * Applies the mask to the object
    *
    * @param arg
    * @return
    */
   private String applyMask(Object arg)
   {
      // Get the message to be logged. Want to do this for both String and non String args.
      String message = arg.toString();
      // Apply any masks.
      message = Mask.maskData(message);
      return message;
   }
}
