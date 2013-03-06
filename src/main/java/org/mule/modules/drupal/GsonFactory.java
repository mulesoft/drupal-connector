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
