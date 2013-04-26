/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
