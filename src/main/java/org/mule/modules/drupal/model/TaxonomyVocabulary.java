/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.model;

import com.google.gson.annotations.SerializedName;

/**
 * Class to groups Taxonomy Terms. See {@link TaxonomyTerm}.
 *
 * @author pablocabrera
 */
public class TaxonomyVocabulary extends DrupalEntity {
    /**
     * Primary Key: Unique vocabulary ID.
     */
    private Integer vid;
    /**
     * The type of hierarchy allowed within the vocabulary. (0 = disabled, 1 = single, 2 = multiple)
     */
    private Integer hierarchy;
    /**
     * The weight of this vocabulary in relation to other vocabularies.
     */
    private Integer weight;

    /**
     * The vocabulary machine name.
     */
    @SerializedName(value = "machine_name")
    private String machineName;
    /**
     * Description of the vocabulary.
     */
    private String description;
    /**
     * Name of the vocabulary
     */
    private String name;
    /**
     * The module which created the vocabulary.
     */
    private String module;

    private static final long serialVersionUID = 1L;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

}
