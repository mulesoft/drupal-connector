/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.model;

import com.google.gson.annotations.SerializedName;

/**
 * Class that represents a File
 *
 * @author pablocabrera
 */
public class File extends DrupalEntity {

    /**
     * File Id
     */
    private Integer fid;
    /**
     * The users.uid of the user who is associated with the file. See {@link User}
     */
    private Integer uid;
    /**
     * Name of the file with no path components. This may differ from the basename of the URI if the file is renamed to avoid overwriting an existing file.
     */
    private String filename;

    /**
     * The files MIME type.
     */
    private String filemime;
    /**
     * The size of the file in bytes.
     */
    private Integer filesize;
    /**
     * A field indicating the status of the file. Two status are defined in core: temporary (0) and permanent (1). Temporary files older than DRUPAL_MAXIMUM_TEMP_FILE_AGE will be removed during a cron run.
     */
    private Integer status;
    /**
     * Timestamp for when the file was added.
     */
    private Integer timestamp;

    /**
     * File content encoded in Base64
     */
    @SerializedName(value = "file")
    private String content;
    /**
     * Uri full path
     */
    @SerializedName(value = "uri_full")
    private String uriFull;
    /**
     * Target Uri
     */
    @SerializedName(value = "target_uri")
    private String targetUri;
    /**
     * Styles
     */
    private Object imageStyles;

    private static final long serialVersionUID = 1342248788461288091L;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilemime() {
        return filemime;
    }

    public void setFilemime(String filemime) {
        this.filemime = filemime;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUriFull() {
        return uriFull;
    }

    public void setUriFull(String uriFull) {
        this.uriFull = uriFull;
    }

    public String getTargetUri() {
        return targetUri;
    }

    public void setTargetUri(String targetUri) {
        this.targetUri = targetUri;
    }

    public Object getImageStyles() {
        return imageStyles;
    }

    public void setImageStyles(Object imageStyles) {
        this.imageStyles = imageStyles;
    }

}
