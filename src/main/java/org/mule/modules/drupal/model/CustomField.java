/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.model;

import java.util.List;
import java.util.Map;

/**
 * Drupal use for all custom fields that are not defined by default a list of maps. The name of the field is the machine name set to it and then comes a list of maps named "und".<p/>
 * In JSON a custom field looks like: <code>"custom_field_machine_name":{"und":[{"property_name":"property value"}]}</code>
 *
 * @author pablocabrera
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
