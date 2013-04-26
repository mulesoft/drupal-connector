/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.model;

import java.util.List;
import java.util.Map;

/**
 * Drupal use for all custom fields that are not defined by default a list of maps. The name of the field is the machine name set to it and then comes a list of maps named "und".<p/>
 * In JSON a custom field looks like: <code>"custom_field_machine_name":{"und":[{"property_name":"property value"}]}</code>
 * @author pablocabrera
 *
 */
public class CustomField {

	/**
	 * List of maps that contains all the fields required to setupd the custom field.
	 */
	@SuppressWarnings("rawtypes")
	List<Map> und;

	@SuppressWarnings("rawtypes")
	public List<Map> getUnd() {
		return und;
	}

	@SuppressWarnings("rawtypes")
	public void setUnd(List<Map> und) {
		this.und = und;
	}
}
