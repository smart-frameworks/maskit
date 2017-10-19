package com.smartframeworks.core.logging;

import com.smartframeworks.core.Mask;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author pavan mummareddi
 */
public class LoggingTest
{

   private static final transient Logger LOG = LoggerFactory.getLogger(LoggingTest.class);
   private static final String ACCOUNT_NUMBER = "4868960000533560";
   private static final String MMN = "FAKEDECISION";
   private static final String ACCOUNT_NUMBER_MASKED = "XXXXXXXXXXXX3560";
   private static final String INVALID_ACCOUNT_NUMBER = "1111111111111111";
   private static final String ACCOUNT_NUMBER_SPACES = "4868 9600 0053 3560";
   private static final String ACCOUNT_NUMBER_SPACES_MASKED = "XXXXXXXXXXXXXXX3560";
   // Visa
   private static final String VISA = "4111111111111111";
   private static final String VISA_DASHES = "4111-1111-1111-1111";
   private static final String VISA_MASKED = "XXXXXXXXXXXX1111";
   private static final String VISA_DASHES_MASKED = "XXXXXXXXXXXXXXX1111";
   // Mastercard
   private static final String MASTERCARD = "5500000000000004";
   private static final String MASTERCARD_DASHES = "5500-0000-0000-0004";
   private static final String MASTERCARD_MASKED = "XXXXXXXXXXXX0004";
   private static final String MASTERCARD_DASHES_MASKED = "XXXXXXXXXXXXXXX0004";
   // AMEX
   private static final String AMERICAN_EXPRESS = "340000000000009";
   private static final String AMERICAN_EXPRESS_MASKED = "XXXXXXXXXXX0009";
   private static final String AMERICAN_EXPRESS_DASHES = "3400-0000-0000-009";
   private static final String AMERICAN_EXPRESS_DASHES_MASKED = "XXXXXXXXXXXXXX0009";
   // Discover
   private static final String DISCOVER = "6011000000000004";
   private static final String DISCOVER_MASKED = "XXXXXXXXXXXX0004";
   private static final String DISCOVER_DASHES = "6011-0000-0000-0004";
   private static final String DISCOVER_DASHES_MASKED = "XXXXXXXXXXXXXXX0004";
   // SSN
   private static final String SSN = "612080342";
   private static final String NON_SSN = "1111111111111111";
   private static final String SSN_DASHES = "612-08-0342";
   private static final String SSN_MASKED = "XXXXX0342";
   private static final String SSN_DASHES_MASKED = "XXXXXXX0342";
   private static final String SSN_SPACES = "612 08 0342";
   private static final String SSN_SPACE_MASKED = "XXXXXXX0342";

   enum Day
   {

      SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
      THURSDAY, FRIDAY, SATURDAY
   }

   @Test
   public void testCreditcardMasking()
   {
      LOG.info("Credit card number is {}", ACCOUNT_NUMBER);
      Assert.assertEquals(Mask.maskData(ACCOUNT_NUMBER), ACCOUNT_NUMBER_MASKED);
   }

   @Test
   public void testCreditcardWithSpacesMasking()
   {
      LOG.info("Credit card number with spaces is {}", ACCOUNT_NUMBER_SPACES);
      Assert.assertEquals(Mask.maskData(ACCOUNT_NUMBER_SPACES), ACCOUNT_NUMBER_SPACES_MASKED);
   }

   @Test
   public void testCreditcardInSentenceMasking()
   {
      final String ACCOUNT_NUMBER_SENTENCE = "abcd " + ACCOUNT_NUMBER + " efgh";
      LOG.info("Credit card number in a sentence is {}", ACCOUNT_NUMBER_SENTENCE);
      Assert.assertEquals(Mask.maskData(ACCOUNT_NUMBER_SENTENCE), "abcd " + ACCOUNT_NUMBER_MASKED + " efgh");
   }
   //@Test
   public void testMMNInXMLMasking()
   {
      final String MMN_SENTENCE = "abcd " + MMN + " efgh";
      LOG.info("Credit card number in a sentence is {}", MMN_SENTENCE);
      Assert.assertEquals(Mask.maskData(MMN_SENTENCE), "abcd " + ACCOUNT_NUMBER_MASKED + " efgh");
   }

   // TODO: fix regex to not mask if value embedded in numbers
   //@Test
   public void testCreditcardEmbeddedInNumbersMasking()
   {
      final String ACCOUNT_NUMBER_EMBEDDED = "123" + ACCOUNT_NUMBER + "1234";
      LOG.info("Credit card number is {}", ACCOUNT_NUMBER_EMBEDDED);
      Assert.assertEquals(Mask.maskData(ACCOUNT_NUMBER_EMBEDDED), "123" + ACCOUNT_NUMBER_MASKED + "1234");
   }

   @Test
   public void testInvalidCreditcardMasking()
   {
      LOG.info("Credit card number is {}", INVALID_ACCOUNT_NUMBER);
      Assert.assertEquals(Mask.maskData(INVALID_ACCOUNT_NUMBER), INVALID_ACCOUNT_NUMBER);
   }

   @Test
   public void testVisaMasking()
   {
      LOG.info("Visa credit card number is {}", VISA);
      Assert.assertEquals(Mask.maskData(VISA), VISA_MASKED);
   }

   @Test
   public void testVisaWithDashesMasking()
   {
      LOG.info("Visa credit card with number dashes is {}", VISA_DASHES);
      Assert.assertEquals(Mask.maskData(VISA_DASHES), VISA_DASHES_MASKED);
   }

   @Test
   public void testMastercardMasking()
   {
      LOG.info("Mastercard credit card number is {}", MASTERCARD);
      Assert.assertEquals(Mask.maskData(MASTERCARD), MASTERCARD_MASKED);
   }

   @Test
   public void testMastercardWithDashesMasking()
   {
      LOG.info("Mastercard credit card number with dashes  is {}", MASTERCARD_DASHES);
      Assert.assertEquals(Mask.maskData(MASTERCARD_DASHES), MASTERCARD_DASHES_MASKED);
   }

   @Test
   public void testAmexMasking()
   {
      LOG.info("Amex credit card number is {}", AMERICAN_EXPRESS);
      Assert.assertEquals(Mask.maskData(AMERICAN_EXPRESS), AMERICAN_EXPRESS_MASKED);
   }

   //@Test
   public void testAmexMaskingWithDashes()
   {
      LOG.info("Amex credit card number with dashes is {}", AMERICAN_EXPRESS_DASHES);
      Assert.assertEquals(Mask.maskData(AMERICAN_EXPRESS_DASHES), AMERICAN_EXPRESS_DASHES_MASKED);
   }

   @Test
   public void testDiscoverMasking()
   {
      LOG.info("Discover credit card number is {}", DISCOVER);
      Assert.assertEquals(Mask.maskData(DISCOVER), DISCOVER_MASKED);
   }

   @Test
   public void testSSNWithDashesMasking()
   {
      LOG.info("SSN with dashes is {}", SSN_DASHES);
      Assert.assertEquals(Mask.maskData(SSN_DASHES), SSN_DASHES_MASKED);

   }

   @Test
   public void testSSNWithStringAndEqualToPrefixedMasking()
   {
      LOG.info("SSN is {}", "SSN=" + SSN);
      Assert.assertEquals(Mask.maskData("SSN=" + SSN), "SSN=" + SSN_MASKED);

   }

   @Test
   public void testSSNWithSpacesMasking()
   {
      LOG.info("SSN space seperated is {}", SSN_SPACES);
      Assert.assertEquals(Mask.maskData(SSN_SPACES), SSN_SPACE_MASKED);
   }

   @Test
   public void testSSNAtBegningOfStringMasking()
   {
      LOG.info("SSN at Begning of the String is {}", SSN + " TestString");
      Assert.assertEquals(Mask.maskData(SSN + " TestString"), SSN_MASKED + " TestString");
   }

   @Test
   public void testSSNAtEndOfStringMasking()
   {
      LOG.info("SSN at End of the String is {}", " TestString " + SSN);
      Assert.assertEquals(Mask.maskData("TestString " + SSN), "TestString " + SSN_MASKED);
   }

   @Test
   public void testSSNAtMiddleOfStringMasking()
   {
      LOG.info("SSN at middle of string with spaces around is {}", " TestString " + SSN + " TestString");
      Assert.
         assertEquals(Mask.maskData("TestString " + SSN + " TestString"), "TestString " + SSN_MASKED + " TestString");
   }

   @Test
   public void testSSNWithDashAndStringAroundMasking()
   {
      LOG.info("SSN with dashes is {} ", "abc " + SSN_DASHES + " def");
      Assert.assertEquals(Mask.maskData("abc " + SSN_DASHES + " def"), "abc " + SSN_DASHES_MASKED + " def");
   }

   @Test
   public void testSSNWithDashAndNumbersAroundMasking()
   {
      LOG.info("SSN with dashes and with numbers around is {} ", "123 " + SSN_DASHES + " 456");
      Assert.assertEquals(Mask.maskData("123 " + SSN_DASHES + " 456"), "123 " + SSN_DASHES_MASKED + " 456");
   }

   @Test
   public void testOnlySSNMasking()
   {
      Assert.assertEquals(Mask.maskData(SSN), SSN_MASKED);
      LOG.info("Only SSN is {}", "ssn =" + SSN);

   }

   @Test
   public void testNotSSNMasking()
   {
      LOG.info("Not SSN is {}", "not masked =" + NON_SSN);
      Assert.assertEquals(Mask.maskData(NON_SSN), NON_SSN);
   }

   @Test
   public void testObjectArratArgs()
   {
      LOG.info(
         "finding transactions by account id... input params: accountId: {}, requestTypeEnum: {}, "
         + "relatedData: {}, fromDate: {}, toDate: {}, statementDate: {}, numberRecsReturned: {}, contextId {}, cc {}",
         new Object[]
         {
            123456, Day.FRIDAY, "related", new Date(), null, new Date(), 56, new Object[]
            {
               "contextId", "contextId"
            }, ACCOUNT_NUMBER
         });
   }
}
