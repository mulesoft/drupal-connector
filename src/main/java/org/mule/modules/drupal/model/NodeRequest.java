/**
 * Mule Drupal Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.drupal.model;

/**
 * Container for the Node requests
 * @author pablocabrera
 *
 */
public class NodeRequest extends DrupalEntity{

	private static final long serialVersionUID = -7818449494968023788L;

	/**
	 * The node used for the request
	 */
	private Node node;

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}
	
}
