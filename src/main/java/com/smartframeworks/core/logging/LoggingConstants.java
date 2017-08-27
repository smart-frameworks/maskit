/*
 * Copyright (c) 2009 BarclaycardUS
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of BarclaycardUS.
 * ("Confidential Information").
 */
package com.smartframeworks.core.logging;

/**
 * The standard logging constants used throughout BarclayCardUS
 *
 * @author pavan mummareddi
 */
public enum LoggingConstants
{

   CONTEXT_ID,
   METHOD_NAME,
   CLASS_NAME,
   TARGET_CLASS_NAME,
   TARGET_METHOD_NAME;

   @Override
   public String toString()
   {
      return this.name();
   }
}
