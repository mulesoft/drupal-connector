/**
 * Mule Drupal Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.drupal.client;

public class DrupalException extends Exception{

	private static final long serialVersionUID = 1L;
	
    public DrupalException(Throwable cause) {
        super(cause);
    }

    public DrupalException(String message, Throwable cause) {
        super(message, cause);
    }

    public DrupalException(String s) {
        super(s);
    }
}
