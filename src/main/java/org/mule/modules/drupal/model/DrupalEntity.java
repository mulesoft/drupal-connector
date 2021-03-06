/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

/**
 * Base class that contains the meta data of the request
 *
 * @author pablocabrera
 */
public class DrupalEntity implements Serializable {

    private static final long serialVersionUID = -6066933965719610253L;

    /**
     * Multidimensional map with meta data
     */
    @SerializedName(value = "rdf_mapping")
    private Object rdfMapping;

    /**
     * Uri to the resource. This field will be populated only when you execute an index on the resource
     */
    private String uri;

    /**
     * All fields that are not default will be placed here. This is a pair of Name field, and CustomField.
     */
    private Map<String, CustomField> customFields;

    public Object getRdfMapping() {
        return rdfMapping;
    }

    public void setRdfMapping(Object rdfMapping) {
        this.rdfMapping = rdfMapping;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, CustomField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map<String, CustomField> customFields) {
        this.customFields = customFields;
    }

}
