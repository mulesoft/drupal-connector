/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import org.mule.modules.drupal.model.Node;
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.User;
import org.mule.modules.drupal.model.File;
import org.mule.modules.drupal.model.TaxonomyTerm;
import org.mule.modules.drupal.model.TaxonomyVocabulary;

/**
 * Enum that contains the resources supported by drupal service module.
 * @author pablocabrera
 *
 */
public enum DrupalCollection {

    Node("node", "node", Node.class),
    Comment("comment", "comment", Comment.class),
    User("user", "user", User.class),
    File("file","file",File.class),
    TaxonomyTerm("taxonomy_term","taxonomy_term",TaxonomyTerm.class),
    TaxonomyVocabulary("taxonomy_vocabulary","taxonomy_vocabulary",TaxonomyVocabulary.class);
    
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
    
    static{
    	knowCustomFields.add("body");
    	knowCustomFields.add("comment_body");
    }
    
    public static boolean IsKnowCustomField(String customField){
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