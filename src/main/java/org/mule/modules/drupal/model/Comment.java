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

import com.google.gson.annotations.SerializedName;
/**
 * Class that represents a comment.
 * 
 * @author pablocabrera
 *
 */
public class Comment  extends DrupalEntity{

	private static final long serialVersionUID = -827408992218353534L;

	/**
	 * Primary Key: Unique comment ID.
	 */
    private Integer cid;
    /**
     * The comment.cid to which this comment is a reply. If set to 0, this comment is not a reply to an existing comment.
     */
    private Integer pid;
    /**
     * The node.nid to which this comment is a reply.Check {@link Node}
     */
    private Integer nid;
    /**
     * The users.uid who authored the comment. If set to 0, this comment was created by an anonymous user. See {@link User}
     */
    private Integer uid;
    /**
     * The comment title.
     */
    private String subject;
    /**
     * The authors host name.
     */
    private String hostname;
    /**
     * The time that the comment was created, as a Unix timestamp.
     */
    private Integer created;
    /**
     * The time that the comment was last edited, as a Unix timestamp.
     */
    private Integer changed;
    /**
     * The published status of a comment. (0 = Not Published, 1 = Published) Default value is 1
     */
    private Integer status;
    /***
     * The vancode representation of the comments place in a thread.
     */
    private String thread;
    /**
     * The comment authors name. Uses users.name if the user is logged in, otherwise uses the value typed into the comment form. See {@link User}
     */
    private String name;    
    /**
     * The comment authors e-mail address from the comment form, if user is anonymous, and the Anonymous users may/must leave their contact information setting is turned on. See {@link User}
     */
    private String mail;
    /**
     * The comment authors home page address from the comment form, if user is anonymous, and the Anonymous users may/must leave their contact information setting is turned on.
     */
    private String homepage;
    /**
     * The languages.language of this comment.
     */
    private String language;
    
    /**
     * Type of the comment
     */
    @SerializedName(value="node_Type")
    private String nodeType;
    
    /**
     * Comment authors registered name
     */
    @SerializedName(value="registered_name")
    private String registeredName;
    
    /**
     * Id of the user
     */
    @SerializedName(value="uuid")
    private String uUid;
    /**
     * Signatureof the user
     */
    private String signature;
    
    /**
     * Signature format
     */
    @SerializedName(value="signature_format")
    private String signatureFormat;
    /**
     * Picture
     */
    private String picture;
    
    /**
     * Boolean to indicate the status
     */
    @SerializedName(value="new")
    private String isNew;
    /**
     * Comment content
     */
    @SerializedName(value="comment_body")
    private CustomField commentBody;

    public Comment(){
    	setPid(0);
    }
    
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getNid() {
		return nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}

	public Integer getChanged() {
		return changed;
	}

	public void setChanged(Integer changed) {
		this.changed = changed;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getThread() {
		return thread;
	}

	public void setThread(String thread) {
		this.thread = thread;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getRegisteredName() {
		return registeredName;
	}

	public void setRegisteredName(String registeredName) {
		this.registeredName = registeredName;
	}

	public String getuUid() {
		return uUid;
	}

	public void setuUid(String uUid) {
		this.uUid = uUid;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignatureFormat() {
		return signatureFormat;
	}

	public void setSignatureFormat(String signatureFormat) {
		this.signatureFormat = signatureFormat;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public CustomField getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(CustomField commentBody) {
		this.commentBody = commentBody;
	}
}
