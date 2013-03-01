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
 * Class that represents a Node. Node are usually the pages and articles
 * @author pablocabrera
 *
 */
public class Node extends DrupalEntity{

	/**
	 * The primary identifier for a node.
	 */
	private Integer nid;
	/**
	 * The users uid that owns this node; initially, this is the user that created it. See {@link User}
	 */
	private Integer uid;
	/**
	 * The current node revision id version identifier.
	 */
	private Integer vid;
	/***
	 * The machine-readable name of this type of this node
	 */
	private String type;
	/**
	 * The languages.language of this node.
	 */
	private String language;
	/**
	 * The title of this node, always treated as non-markup plain text.
	 */
	private String title;
	
	/**
	 * Boolean indicating whether the node is published (visible to non-administrators).
	 */
	private Integer status;
	/**
	 * Whether comments are allowed on this node: 0 = no, 1 = closed (read only), 2 = open (read/write).
	 */
	private Integer comment;
	/**
	 * Boolean indicating whether the node should be displayed on the front page.
	 */
	private Integer promote;
	/**
	 * Boolean indicating whether the node should be displayed at the top of lists in which it appears.
	 */
	private Integer sticky;
	/**
	 * The translation set id for this node, which equals the node id of the source post in each set.
	 */
	private Integer tnid;
	/**
	 * A boolean indicating whether this translation page needs to be updated.
	 */
	private Integer translated;
	/**
	 * A Unix timestamp indicating when this version was created.
	 */
	@SerializedName(value="revision_timestamp")
	private Integer revisionTimestamp;
	
	/**
	 * The users.uid that created this version. {@link User}}
	 */
	@SerializedName(value="revision_uid")
	private Integer revisionUid;
	
	/**
	 * Mutidimensional map that represents the body of the node
	 */
	private CustomField body;
	
	/**
	 * The Unix timestamp when the node was created.
	 */
	private Integer created;
	/**
	 * The Unix timestamp when the node was most recently saved.
	 */
	private Integer changed;
	
	/**
	 * The comment.cid of the last comment.
	 */
    private Integer cid;
    
    /**
     * The timestamp of the last comment that was posted within this node, from comment.changed.
     */
    @SerializedName(value="last_comment_timestamp")
    private Integer lastCommentTimestamp;
    
    /**
     * The name of the latest author to post a comment on this node, from comment.name.
     */
    @SerializedName(value="last_comment_name")
    private String lastCommentName;
    
    /**
     * The user ID of the latest author to post a comment on this node, from comment.uid.
     */
    @SerializedName(value="last_comment_uid")
    private Integer lastCommentUid;
    
    /**
     * The total number of comments on this node.
     */
    @SerializedName(value="comment_count")
    private Integer commentCount;
    
    /**
     * Name
     */
    private String name;
    /**
     * Picture
     */
    private String picture;
    /**
     * Data
     */
    private String data;
    /**
     * The path of the node
     */
    private String path;

	private static final long serialVersionUID = -5132168862185097644L;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNid() {
		return nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public Integer getPromote() {
		return promote;
	}

	public void setPromote(Integer promote) {
		this.promote = promote;
	}

	public Integer getSticky() {
		return sticky;
	}

	public void setSticky(Integer sticky) {
		this.sticky = sticky;
	}

	public Integer getTranslated() {
		return translated;
	}

	public void setTranslated(Integer translated) {
		this.translated = translated;
	}

	public Integer getRevisionTimestamp() {
		return revisionTimestamp;
	}

	public void setRevisionTimestamp(Integer revisionTimestamp) {
		this.revisionTimestamp = revisionTimestamp;
	}

	public Integer getRevisionUid() {
		return revisionUid;
	}

	public void setRevisionUid(Integer revisionUid) {
		this.revisionUid = revisionUid;
	}

	public CustomField getBody() {
		return body;
	}

	public void setBody(CustomField body) {
		this.body = body;
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

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getLastCommentTimestamp() {
		return lastCommentTimestamp;
	}

	public void setLastCommentTimestamp(Integer lastCommentTimestamp) {
		this.lastCommentTimestamp = lastCommentTimestamp;
	}

	public String getLastCommentName() {
		return lastCommentName;
	}

	public void setLastCommentName(String lastCommentName) {
		this.lastCommentName = lastCommentName;
	}

	public Integer getLastCommentUid() {
		return lastCommentUid;
	}

	public void setLastCommentUid(Integer lastCommentUid) {
		this.lastCommentUid = lastCommentUid;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public Integer getTnid() {
		return tnid;
	}

	public void setTnid(Integer tnid) {
		this.tnid = tnid;
	}

}
