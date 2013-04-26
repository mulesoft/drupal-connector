/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.model;

import java.util.HashMap;

import com.google.gson.annotations.SerializedName;
/**
 * Class that represents Users
 * @author pablocabrera
 *
 */
public class User extends DrupalEntity {

	private static final long serialVersionUID = -479087343702501975L;
	
	/**
	 * Primary Key: Unique user ID.
	 */
	private Integer uid;
	/**
	 * Unique user name.
	 */
	private String name;
	/**
	 * Users e-mail address.
	 */
	private String mail;
	/**
	 * User password.
	 */
	@SerializedName(value="pass")
	private String password;
	/**
	 * Users default theme.
	 */
	private String theme;
	/**
	 * Users signature.
	 */
	private String signature;
	/**
	 * The filter_format.format of the signature.
	 */
	@SerializedName(value="signature_format")
	private String signatureFormat;
	/**
	 * Timestamp for when user was created
	 */
	private Integer created;
	/**
	 * Timestamp for previous time user accessed the site.
	 */
	private Integer access;
	/**
	 * Timestamp for users last login.
	 */
	private Integer login;
	/**
	 * Whether the user is active(1) or blocked(0).
	 */
	private Integer status;
	/**
	 * Users time zone.
	 */
	private String timezones;
	/**
	 * Users default language.
	 */
	private String language;
	
	/**
	 * file_managed.fid of users picture. Check {@link File}
	 */
	private Integer picture;
	/**
	 * E-mail address used for initial account creation.
	 */
	private String init;
	/**
	 * A serialized array of name value pairs that are related to the user.  
	 * Use of this field is discouraged and it will likely disappear in a future...
	 */
	private String data;
	/**
	 * A map that contains pairs of role id and role description for the user.
	 */
	private HashMap<Integer,String> roles;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
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
	public Integer getCreated() {
		return created;
	}
	public void setCreated(Integer created) {
		this.created = created;
	}
	public Integer getAccess() {
		return access;
	}
	public void setAccess(Integer access) {
		this.access = access;
	}
	public Integer getLogin() {
		return login;
	}
	public void setLogin(Integer login) {
		this.login = login;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTimezones() {
		return timezones;
	}
	public void setTimezones(String timezones) {
		this.timezones = timezones;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Integer getPicture() {
		return picture;
	}
	public void setPicture(Integer picture) {
		this.picture = picture;
	}
	public String getInit() {
		return init;
	}
	public void setInit(String init) {
		this.init = init;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public HashMap<Integer,String> getRoles() {
		return roles;
	}
	public void setRoles(HashMap<Integer,String> roles) {
		this.roles = roles;
	}

}
