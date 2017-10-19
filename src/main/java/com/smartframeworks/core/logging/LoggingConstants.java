package com.smartframeworks.core.logging;

/**
 * Logging constants
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
