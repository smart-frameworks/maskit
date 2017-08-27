package com.smartframeworks.core.data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

public class RequestStopWatch
{

   private String callID;
   private String callName;
   private Long startTime;
   private Long endTime;
   private static final Long ZERO_LONG = Long.valueOf(0);

   /**
    * @return the callID
    */
   public String getCallID()
   {
      return callID;
   }

   /**
    * @param callID the callID to set
    */
   public void setCallID(String callID)
   {
      this.callID = callID;
   }

   /**
    * @return the callName
    */
   public String getCallName()
   {
      return callName;
   }

   /**
    * @param callName the callName to set
    */
   public void setCallName(String callName)
   {
      this.callName = callName;
   }

   /**
    * @return the startTime
    */
   public Long getStartTime()
   {
      return startTime;
   }

   /**
    * @param startTime the startTime to set
    */
   public void setStartTime(Long startTime)
   {
      this.startTime = startTime;
   }

   /**
    * @return the endTime
    */
   public Long getEndTime()
   {
      return endTime;
   }

   /**
    * @param endTime the endTime to set
    */
   public void setEndTime(Long endTime)
   {
      this.endTime = endTime;
   }

   public String getTimeInMillisAsDate(Long time)
   {
      try
      {

         SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyyMMdd HH:mm:ss.SSS");
         dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
         return dateFormat.format(new Date(time.longValue()));
      }
      catch (Exception e)
      {
         return "";
      }
   }

   public String toString()
   {
      StringBuilder buffer = new StringBuilder();
      buffer.append(this.getCallID()).append(":");
      long elapsedTime = 0;
      if (this.getStartTime() != null && this.getEndTime() != null)
      {
         elapsedTime = this.getEndTime().longValue() - this.getStartTime().longValue();
      }
      buffer.append(elapsedTime);
      return buffer.toString();
   }

   public String getElapsedTime()
   {
      long elapsedTime = 0;
      if (this.getStartTime() != null && this.getEndTime() != null)
      {
         elapsedTime = this.getEndTime().longValue() - this.getStartTime().longValue();
      }
      return elapsedTime + " ms";
   }

   public static Comparator<RequestStopWatch> getComparator()
   {
      return new RequestStopWatchComparator();
   }

   private static class RequestStopWatchComparator implements Comparator<RequestStopWatch>, Serializable
   {

      @Override
      public int compare(RequestStopWatch rsw1, RequestStopWatch rsw2)
      {
         int result = 0;
         try
         {
            if (rsw1 == null && rsw2 == null)
            {
               return 0;
            }

            if (rsw1 == null || rsw2 == null)
            {
               return -1;
            }
            
            Long stopTimeInMillis1 = null;
            Long stopTimeInMillis2 = null;
            stopTimeInMillis1 = rsw1.getEndTime() != null ? rsw1.getEndTime() : ZERO_LONG;
            stopTimeInMillis2 = rsw2.getEndTime() != null ? rsw2.getEndTime() : ZERO_LONG;
            result = stopTimeInMillis1.compareTo(stopTimeInMillis2);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
         return result;
      }
   }

   public void start()
   {
      try
      {
         setStartTime(Calendar.getInstance().getTimeInMillis());
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void stop()
   {
      try
      {
         setEndTime(Calendar.getInstance().getTimeInMillis());
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

}
