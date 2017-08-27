package com.smartframeworks.core.logging;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class LogSwitcher
{

   public static void usePerfLog()
   {
      LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
      context.reset();

      try
      {
         JoranConfigurator configurator = new JoranConfigurator();
         configurator.setContext(context);
         // context.reset();
         // Call context.reset() to clear any previous configuration, e.g.
         // default
         // configuration. For multi-step configuration, omit calling
         // context.reset().
         configurator.doConfigure("src/test/resources/logback-perf-test.xml");
      }
      catch (JoranException je)
      {
         // StatusPrinter will handle this
      }
      //StatusPrinter.printInCaseOfErrorsOrWarnings(context);
   }

   public static void useDefaultLog()
   {
      LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
      context.reset();

      try
      {
         JoranConfigurator configurator = new JoranConfigurator();
         configurator.setContext(context);
         // Call context.reset() to clear any previous configuration, e.g.
         // default
         // configuration. For multi-step configuration, omit calling
         // context.reset().
         configurator.doConfigure("src/test/resources/logback-test.xml");
      }
      catch (JoranException je)
      {
         // StatusPrinter will handle this
      }
      //StatusPrinter.printInCaseOfErrorsOrWarnings(context);
   }

}
