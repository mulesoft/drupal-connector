/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * A container for the request made to get the amount of comments (and new comments) related to a node.
 * @author pablocabrera
 *
 */
public class CountRequest implements Serializable{

	private static final long serialVersionUID = -1253208409778724544L;

	@SerializedName(value="nid")
	private int nodeId;
	
	private int since;

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public int getSince() {
		return since;
	}

	public void setSince(int since) {
		this.since = since;
	}
}
