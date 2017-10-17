/*
 * 
 *  Copyright (c) 2010 BarclaycardUS
 *  All rights reserved.
 * 
 *  This software is the confidential and proprietary information of BarclaycardUS.
 *  ("Confidential Information").
 * 
 *  File: Mask.java
 *  Created: Nov 12, 2010
 * 
 */
package com.smartframeworks.core.util;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.LoggerFactory;

/**
 * Class used to mask sensitive data.
 *
 * @author pavan mummareddi
 */
public final class Mask
{
   private static final Logger LOG =  LoggerFactory.getLogger(Mask.class);
   private static final String MASK_CHARS = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
   // Create the patterns we want to match.
   private static final Pattern CREDITCARD_PATTERN_GROUPED = Pattern.compile(
      "(?:4\\d{3}[ -]*\\d{4}[ -]*\\d{4}[ -]*\\d(?:\\d{3})?|5[1-5]\\d{2}[ -]*\\d{4}[ -]*\\d{4}[ -]*\\d{4}|6(?:011|5[0-9]{2})[ -]*\\d{4}[ -]*\\d{4}[ -]*\\d{4}|3[47]\\d{2}[ -]*\\d{6}[ -]*\\d{5}|3(?:0[0-5]|[68][0-9])\\d[ -]*\\d{6}[ -]*\\d{4}|(?:2131|1800)[ -]*\\d{6}[ -]*\\d{5}|35\\d{2}[ -]*\\d{4}[ -]*\\d{4}[ -]*\\d{4})",
      Pattern.CASE_INSENSITIVE
      | Pattern.DOTALL | Pattern.MULTILINE);
   private static final Pattern CREDITCARD_PATTERN_BARE = Pattern.compile(
      "(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})",
      Pattern.CASE_INSENSITIVE
      | Pattern.DOTALL | Pattern.MULTILINE);
   private static final Pattern SSN_PATTERN = Pattern.compile("\\b\\d{3}[ -]?\\d{2}[ -]?\\d{4}\\b",
      Pattern.CASE_INSENSITIVE
      | Pattern.DOTALL | Pattern.MULTILINE);
   private static final int UNMASKED_CHARS_LENGTH = 4;
   private final static Pattern CARD_VER_IF_NBR_PATTERN = Pattern.compile("(?<=<cardVerifNbr>)(.*?)(?=</cardVerifNbr>)",Pattern.CASE_INSENSITIVE|Pattern.DOTALL | Pattern.MULTILINE);
   private final static Pattern PIN_PATTERN = Pattern.compile("(?<=<PIN>)(.*?)(?=</PIN>)",Pattern.CASE_INSENSITIVE|Pattern.DOTALL | Pattern.MULTILINE);

   private static final List<Pattern> REGEX_EXPRESSIONS = new ArrayList<Pattern>();

   static
   {
      REGEX_EXPRESSIONS.add(CREDITCARD_PATTERN_GROUPED);
      REGEX_EXPRESSIONS.add(CREDITCARD_PATTERN_BARE);
      REGEX_EXPRESSIONS.add(SSN_PATTERN);
      REGEX_EXPRESSIONS.add(CARD_VER_IF_NBR_PATTERN);
       REGEX_EXPRESSIONS.add(PIN_PATTERN);

   }

   // Prevent instantiation of this class
   private Mask()
   {
   }

   /**
    * Mask sensitive data.
    *
    * @param message The message to apply the mask to.
    * @return the modified message.
    */
   public static String maskData(String message)
   {
      String maskedMessage = message;
      for (Pattern pattern : REGEX_EXPRESSIONS)
      {
         try
         {
            maskedMessage = maskData(maskedMessage, pattern);
         }
         catch (Exception ex)
         {
            LOG.info(ex.toString());
         }
      }
      return maskedMessage;
   }

   /**
    * Mask sensitive data.
    *
    * @param message The message to apply the mask to.
    * @return the modified message.
    */
   private static String maskData(String message, Pattern pattern) throws Exception
   {
      String returnedMessage = message;
      String matchedPart = null;
      StringBuilder part;
      StringBuilder maskedMessage = null;
      // If the pattern matches this message, mask it.
      Matcher matcher = pattern.matcher(message);

      // Loop through the matches.      
      while (matcher.find())
      {
         // Get the matched part.
         matchedPart = matcher.group();
           
         int maskLength = matchedPart.length() - UNMASKED_CHARS_LENGTH ;
         maskLength = maskLength > UNMASKED_CHARS_LENGTH ? maskLength : matchedPart.length();
         // Mask all but last n characters.           
         part = new StringBuilder();
         part.append(MASK_CHARS.substring(0, maskLength))
           .append(matchedPart.substring(matchedPart.length() - (matchedPart.length() - maskLength)));
         // Inject this part.         
         maskedMessage = new StringBuilder();
         maskedMessage.append(message.substring(0, matcher.start())).append(part)
            .append(message.substring(matcher.end()));
         //updating the original message as we go. Bug fix - failed in case of multiple matches
         message = maskedMessage.toString();    
      }

      if (maskedMessage != null)
      {
         returnedMessage = maskedMessage.toString().trim();
      }

      return returnedMessage;
   }
}
