/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal;


import com.google.gson.*;
import org.mule.modules.drupal.model.CustomField;
import org.mule.modules.drupal.model.DrupalEntity;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Deserializer to handle custom fields deserialization.
 *
 * @author pablocabrera
 */
public class DrupalEntityDeserealizer implements JsonDeserializer<DrupalEntity> {

    @Override
    public DrupalEntity deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
        Gson gson = GsonFactory.getGson();
        DrupalEntity entity = new DrupalEntity();
        JsonObject jsonObject = json.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> members = jsonObject.entrySet();
        Map<String, CustomField> customFields = new HashMap<String, CustomField>();

        for (Map.Entry<String, JsonElement> entry : members) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            if (value.isJsonObject()) {
                if (isCustomField(value) && !DrupalCollection.IsKnowCustomField(key)) {
                    customFields.put(key, gson.fromJson(value, CustomField.class));
                }
            }
        }

        entity.setCustomFields(customFields);

        return entity;
    }

    /**
     * Custom Fields come serialized like "custom_field_machine_name":{"und":[{"property_name":"property value"}]}
     *
     * @param element Element we want to check if it is a custom field
     * @return true if the element has the format expected false otherwise.
     */
    private boolean isCustomField(final JsonElement element) {
        boolean isCustomField = false;
        if (element.isJsonObject()) {
            JsonObject content = element.getAsJsonObject();
            JsonElement undField = content.get("und");
            if (undField != null) {
                isCustomField = undField.isJsonArray();
            }
        }
        return isCustomField;
    }
}
