/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.model;

/**
 * Container for the Comment requests
 *
 * @author pablocabrera
 */
public class CommentRequest extends DrupalEntity {

    private static final long serialVersionUID = -6704227118900821121L;

    private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
