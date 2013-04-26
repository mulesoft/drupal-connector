/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal;


import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.mule.modules.drupal.model.CustomField;
import org.mule.modules.drupal.model.DrupalEntity;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
/**
 * Deserializer to handle custom fields deserialization.
 * @author pablocabrera
 *
 */
public class DrupalEntityDeserealizer implements JsonDeserializer<DrupalEntity> {

	@Override
	public DrupalEntity deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		Gson gson = GsonFactory.getGson();
		DrupalEntity entity=new DrupalEntity();
		JsonObject jsonObject=json.getAsJsonObject();
		Set<Map.Entry<String, JsonElement>> members = jsonObject.entrySet();
		Map<String,CustomField> customFields=new HashMap<String,CustomField>();
		
		for (Map.Entry<String, JsonElement> entry : members) {
			String key = entry.getKey();
			JsonElement value = entry.getValue();
			if(value.isJsonObject()){
				if(isCustomField(value) && !DrupalCollection.IsKnowCustomField(key)){
					customFields.put(key,gson.fromJson(value, CustomField.class));
				}
			}
		}
		
		entity.setCustomFields(customFields);
		
		return entity;
	}

	/**
	 * Custom Fields come serialized like "custom_field_machine_name":{"und":[{"property_name":"property value"}]}
	 * @param element Element we want to check if it is a custom field
	 * @return true if the element has the format expected false otherwise.
	 */
	private boolean isCustomField(final JsonElement element){
		boolean isCustomField = false;
		if(element.isJsonObject()){
			JsonObject content = element.getAsJsonObject();
			JsonElement undField = content.get("und");
			if( undField != null ){
				isCustomField = undField.isJsonArray();	
			}
		}
		return isCustomField;
	}
}
