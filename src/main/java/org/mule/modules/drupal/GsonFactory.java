/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal;

import org.mule.modules.drupal.model.DrupalEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * GsonFactory that creates a Gson with custom serializers and deserializers.
 * 
 * @author pablocabrera
 *
 */
public abstract class GsonFactory {
	
	private static Gson gson = null;
	
	public static Gson getGson() {
	
		 
		if (gson == null) {
			gson = new GsonBuilder()
				.registerTypeAdapter(DrupalEntity.class, new DrupalEntityDeserealizer())
				.registerTypeAdapter(DrupalEntity.class, new CustomFieldsMapSerializer())
				.create();
		}
		
		return gson;
	}

}
