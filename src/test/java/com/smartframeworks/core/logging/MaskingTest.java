/*
 * 
 *  Copyright (c) 2014 BarclaycardUS
 *  All rights reserved.
 * 
 *  This software is the confidential and proprietary information of BarclaycardUS.
 *  ("Confidential Information").
 * 
 *  File: MaskingTest.java
 *  Created: Feb 3, 2014
 * 
 */
package com.smartframeworks.core.logging;

import com.smartframeworks.core.util.Mask;
import org.testng.annotations.Test;
import org.testng.Assert;

/**
 *
 * @author pavan mummareddi
 */
public class MaskingTest
{

   @Test
   public void testMultipleMatches()
   {
      String input = "4868960001169679 test 4868960001169679 test 4868960001169679 test 4868960001169679";
      String maskData = Mask.maskData(input);
      boolean contains = maskData.contains("4868960001169679");
      Assert.assertFalse(contains);
   }

   @Test
   public void testSingleMatches()
   {
      String input = "4868960001169679";
      String maskData = Mask.maskData(input);
      boolean contains = maskData.contains("4868960001169679");
      Assert.assertFalse(contains);
   }

   @Test
   public void testPinMatches()
   {
      String input = "<mntMultiRequest cardNbr=\"4325674325424955\"><maintainRequest xmlns:xsi=\"http://www.w3.org"
         + "/2001/XMLSchema-instance\" version=\"1.3.0\" xsi:type=\"mntPINNbrRequestType\">"
         + "<custID>XXXXX1074</custID><PIN>02</PIn></maintainRequest></mntMultiRequest>";
      String maskData = Mask.maskData(input);
      boolean contains = maskData.contains("02");
      Assert.assertFalse(contains, maskData);
   }

   @Test
   public void testVerifDataMatches()
   {
      String input = "<mntMultiRequest cardNbr=\"4325674325424955\"><maintainRequest xmlns:xsi=\"http://www.w3.org"
         + "/2001/XMLSchema-instance\" version=\"1.3.0\" xsi:type=\"mntPINNbrRequestType\">"
         + "<cardVerifNbr>\n074\n</CardVerifNbr>\n<PIN>6249</PIN></maintainRequest></mntMultiRequest>";
      String maskData = Mask.maskData(input);
      boolean contains = maskData.contains("074");
      Assert.assertFalse(contains);
   }
   
   @Test
   public void testPinMatchesMultiplePIN()
   {
      String input = "<mntMultiRequest cardNbr=\"4325674325424955\"><maintainRequest xmlns:xsi=\"http://www.w3.org"
         + "/2001/XMLSchema-instance\" version=\"1.3.0\" xsi:type=\"mntPINNbrRequestType\">"
         + "<custID>XXXXX1074</custID><PIN>02</PIn><pIn>\n1234\n</pin><pin>1234</pin><cardType>aaa</cardType><pin>1234</pin></maintainRequest></mntMultiRequest>";
      String maskData = Mask.maskData(input);
      boolean contains = maskData.contains("1234");
      Assert.assertFalse(contains, maskData);
   }

}
