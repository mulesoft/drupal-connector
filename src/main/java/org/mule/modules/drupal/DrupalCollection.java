/**
 * Mule Drupal Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
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

public enum DrupalCollection {

    Node("node", "node", Node.class),
    Comment("comment", "comment", Comment.class),
    User("user", "user", User.class),
    File("file","file",File.class),
    TaxonomyTerm("taxonomy_term","taxonomy_term",TaxonomyTerm.class),
    TaxonomyVocabulary("taxonomy_vocabulary","taxonomy_vocabulary",TaxonomyVocabulary.class);
    
    private String entityName;
    private String endpoint;
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