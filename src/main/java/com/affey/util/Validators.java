/*************************************************************************
 *
 * ADOBE CONFIDENTIAL
 * __________________
 *
 *  Copyright 2012 Adobe Systems Incorporated
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 **************************************************************************/
package com.affey.util;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Validators {

  private Validators(){}

  public static boolean patternValidator(String input, String regex) {
    try {
      return Pattern.matches(regex, input);
    } catch (PatternSyntaxException pse) {
      return false;
    }
  }

public static boolean patternValidator(String[] movieActors, String regex) {
	try {
		for(String actor: movieActors){
			if(!Pattern.matches(regex, actor))
				return false;
		}
		return true;
	   } catch (PatternSyntaxException pse) {
	      return false;
	   }
}
}
