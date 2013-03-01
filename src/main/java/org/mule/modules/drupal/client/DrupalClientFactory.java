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

public class DrupalClientFactory {

	public static DrupalClient getClient(String server, int port, String apiUrl) {
		return new DrupalRestClient(server, port, apiUrl);
	}
}
