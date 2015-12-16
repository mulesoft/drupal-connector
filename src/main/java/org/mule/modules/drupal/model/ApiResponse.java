/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.model;

import java.io.Serializable;
import java.util.List;

public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -2928734374594411744L;

    private List<T> records;

    public List<T> getRecords() {
        return this.records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
