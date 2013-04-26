/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
