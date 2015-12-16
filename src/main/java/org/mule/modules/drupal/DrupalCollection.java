/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal;

import org.mule.modules.drupal.model.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Enum that contains the resources supported by drupal service module.
 *
 * @author pablocabrera
 */
public enum DrupalCollection {

    Node("node", "node", Node.class),
    Comment("comment", "comment", Comment.class),
    User("user", "user", User.class),
    File("file", "file", File.class),
    TaxonomyTerm("taxonomy_term", "taxonomy_term", TaxonomyTerm.class),
    TaxonomyVocabulary("taxonomy_vocabulary", "taxonomy_vocabulary", TaxonomyVocabulary.class);

    /**
     * Name of the resource
     */
    private String entityName;
    /**
     * alias used for the resource url. By default this value is the same as the entity name.
     */
    private String endpoint;

    /**
     * Java class related to the resource
     */
    private Class<?> type;

    private static final Map<String, DrupalCollection> name2Instance = new HashMap<String, DrupalCollection>();

    static {
        for (DrupalCollection collection : DrupalCollection.values()) {
            name2Instance.put(collection.getEntityName(), collection);
        }
    }

    public static DrupalCollection getByCollectionName(String collectionName) {
        return name2Instance.get(collectionName);
    }


    private static Set<String> knowCustomFields = new HashSet<String>();

    static {
        knowCustomFields.add("body");
        knowCustomFields.add("comment_body");
    }

    public static boolean IsKnowCustomField(String customField) {
        return knowCustomFields.contains(customField);
    }

    private DrupalCollection(String endpoint, String entityName, Class<?> collectionClass) {
        this.entityName = entityName;
        this.endpoint = endpoint;
        this.type = collectionClass;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpointName(String endpoint) {
        this.endpoint = endpoint;
    }

    public Class<?> getType() {
        return type;
    }

}