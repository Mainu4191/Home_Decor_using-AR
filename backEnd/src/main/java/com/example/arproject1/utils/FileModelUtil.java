package com.example.arproject1.utils;

public class FileModelUtil {
    /*
     * name: event.target.files[0].name,
     * size: event.target.files[0].size, // size in bytes
     * type: event.target.files[0].type,
     * lastModified: event.target.files[0].lastModified,
     * fileExtension: event.target.files[0].name.split(".")[1]
     */

    private String name;
    private long size;
    private String type;
    private long lastModified;
    private String fileExtension;
    private String file;

    public FileModelUtil() {
    }

    public FileModelUtil(String name, long size, String type, long lastModified, String fileExtension, String file) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.lastModified = lastModified;
        this.fileExtension = fileExtension;
        this.file = file;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public String getFileExtension() {
        return this.fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }

}
