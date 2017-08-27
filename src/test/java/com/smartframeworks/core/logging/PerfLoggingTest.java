/*
 * 
 * Copyright (c) 2010 BarclaycardUS All rights reserved.
 * 
 * This software is the confidential and proprietary information of
 * BarclaycardUS. ("Confidential Information").
 * 
 * File: LoggingTest.java Created: Jul 2, 2010
 */
package com.smartframeworks.core.logging;

import com.smartframeworks.core.logging.annotations.LogMethodPerformance;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

/**
 *
 * @author pavan mummareddi
 */
public class PerfLoggingTest
{

   Logger logger = Logger.getLogger(PerfLoggingTest.class);

   @Test
   @LogMethodPerformance(prefix = " prefix is \"junit [annotation test]\"", suffix = " suffix")
   public void testPerformanceLoggingWithPerfLogFile() throws Exception
   {
      LogSwitcher.usePerfLog();

      try
      {
         Thread.sleep(1000);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
   }

   @Test
   @LogMethodPerformance(prefix = " prefix is \"junit [annotation test]\"", suffix = " suffix")
   public void testPerformanceLoggingWithoutPerfLogFile() throws Exception
   {
      LogSwitcher.useDefaultLog();

      try
      {
         Thread.sleep(1000);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
   }

   @Test
   @LogMethodPerformance(prefix = " prefix is \"junit [annotation test]\"", suffix = " suffix")
   public void testPLWithPLFileForCodeHavingLogUsingPerfLog() throws Exception
   {
      LogSwitcher.usePerfLog();

      logger.info("Executing testPLWithPLFileForCodeHavingLogUsingPerfLog");
      try
      {
         Thread.sleep(1000);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
   }

   @Test
   @LogMethodPerformance(prefix = " prefix is \"junit [annotation test]\"", suffix = " suffix")
   public void testPLWithPLFileForCodeNotHavingLogUsingPerfLog() throws Exception
   {
      LogSwitcher.usePerfLog();

      try
      {
         Thread.sleep(1000);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
   }

   @Test
   @LogMethodPerformance(prefix = " prefix is \"junit [annotation test]\"", suffix = " suffix")
   public void testPLWithPLFileForCodeHavingLogUsingDefaultLog() throws Exception
   {
      LogSwitcher.useDefaultLog();
      logger.info("Executing testPLWithPLFileForCodeHavingLogUsingDefaultLog");
      try
      {
         Thread.sleep(1000);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
   }

   @Test
   @LogMethodPerformance(prefix = " prefix is \"junit [annotation test]\"", suffix = " suffix")
   public void testPLWithPLFileForCodeNotHavingLogUsingDefaultLog() throws Exception
   {
      LogSwitcher.useDefaultLog();

      try
      {
         Thread.sleep(1000);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
   }

   @Test
   @LogMethodPerformance(prefix = " prefix is \"junit [annotation test]\"", suffix = " suffix")
   public void testPLWithPLFileWithAnnotationParams() throws Exception
   {
      LogSwitcher.useDefaultLog();

      try
      {
         Thread.sleep(1000);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
   }

}
