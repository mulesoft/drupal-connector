/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * A container for the request made to get the amount of comments (and new comments) related to a node.
 *
 * @author pablocabrera
 */
public class CountRequest implements Serializable {

    private static final long serialVersionUID = -1253208409778724544L;

    @SerializedName(value = "nid")
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
