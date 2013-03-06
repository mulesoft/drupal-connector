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
/**
 * Drupal Rest Client Factory.
 * @author pablocabrera
 *
 */
public class DrupalClientFactory {

	/**
	 * Returns an instance of {@link DrupalRestClient}
	 * @param server Endpoint to connect
	 * @param port	Port number
	 * @param apiUrl Url of the api under the server
	 * @return Instance configured with the values. 
	 */
	public static DrupalClient getClient(String server, int port, String apiUrl) {
		return new DrupalRestClient(server, port, apiUrl);
	}
}
