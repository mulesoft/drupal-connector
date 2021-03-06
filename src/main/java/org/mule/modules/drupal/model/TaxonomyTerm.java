/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Class that represent term references in articles
 *
 * @author pablocabrera
 */
public class TaxonomyTerm extends DrupalEntity {

    private static final long serialVersionUID = 6818911701829311027L;

    /**
     * A description of the term
     */
    private String description;
    /**
     * The filter_format.format of the description
     */
    private String format;
    /**
     * The term name
     */
    private String name;
    /**
     * Primary Key: Unique term ID.
     */
    private Integer tid;
    /**
     * The taxonomy vocabulary vid of the vocabulary to which the term is assigned. See {@link TaxonomyVocabulary}
     */
    private Integer vid;

    /**
     * The weight of this term in relation to other terms.
     */
    private Integer weight;

    /**
     * Depth of the node in the vocabulary tree
     */
    private Integer depth;

    /**
     * List of term Id that of wich this is considered a son.
     */
    private List<Integer> parents;
    /**
     * The vocabulary machine name of the vocabulary to witch the term is assigned. See {@link TaxonomyVocabulary}
     */
    @SerializedName(value = "vocabulary_machine_name")
    private String vocabularyMachineName;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getVocabularyMachineName() {
        return vocabularyMachineName;
    }

    public void setVocabularyMachineName(String vocabularyMachineName) {
        this.vocabularyMachineName = vocabularyMachineName;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public List<Integer> getParents() {
        return parents;
    }

    public void setParents(List<Integer> parents) {
        this.parents = parents;
    }
}
