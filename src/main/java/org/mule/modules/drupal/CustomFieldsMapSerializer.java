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

import java.lang.reflect.Type;
import java.util.Map.Entry;

import org.mule.modules.drupal.model.DrupalEntity;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Class used to serialize drupal entities.
 * @author pablocabrera
 *
 */
public class CustomFieldsMapSerializer implements JsonSerializer<DrupalEntity>{

	private static final String CUSTOM_FIELDS = "customFields";
	
	@Override
	public JsonElement serialize(DrupalEntity src, Type typeOfSrc,
			JsonSerializationContext context) {
		Gson gson = GsonFactory.getGson();

		JsonObject jObj = (JsonObject)gson.toJsonTree(src);
		if(isARequestHolder(jObj)){
			Entry<String,JsonElement> entry = jObj.entrySet().iterator().next();
			if( entry.getValue() != null){
				promoteCustomFieldsToParent( entry.getValue().getAsJsonObject() );
			}
		}
		else{
			promoteCustomFieldsToParent(jObj);			
		}
		
		return jObj;
	}
	/**
	 * The DrupalEntities that contain just one field are place holders for the object that really contains all the data, but for some entities drupal encapsulate them inside a request.
	 * @param jObj the tree JSON root that we will check
	 * @return true is it only has 1 son
	 */
	private boolean isARequestHolder(JsonObject jObj) {
		return jObj.entrySet().size()==1;
	}

	/**
	 * Move one lvl above the content of the custom fields map so that drupal can process the custom fields.
	 * @param drupalEntity JSON representation of a drupal entity
	 */
	private void promoteCustomFieldsToParent(JsonObject drupalEntity) {
		if(drupalEntity != null){
			JsonObject customFields = drupalEntity.getAsJsonObject(CUSTOM_FIELDS);
			if(customFields!=null){
				if(customFields.isJsonObject()){
					for(Entry<String,JsonElement> entry : customFields.entrySet()){
						drupalEntity.add(entry.getKey(),entry.getValue());
					}
				}
			}
			drupalEntity.remove(CUSTOM_FIELDS);
		}
	}
}
