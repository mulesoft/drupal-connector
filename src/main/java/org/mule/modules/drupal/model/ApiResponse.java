/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.model;

import java.io.Serializable;
import java.util.List;

public class ApiResponse <T> implements Serializable{

	private static final long serialVersionUID = -2928734374594411744L;

	private List<T> records;

    public List<T> getRecords() {
        return this.records;
    }
    
    public void setRecords(List<T> records) {
		this.records = records;
	}
}
