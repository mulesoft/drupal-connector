/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.model;

/**
 * Container for the Comment requests
 * @author pablocabrera
 *
 */
public class CommentRequest extends DrupalEntity{

	private static final long serialVersionUID = -6704227118900821121L;
	
	private Comment comment;
	
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
}
