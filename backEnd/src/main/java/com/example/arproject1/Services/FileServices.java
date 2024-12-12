package com.example.arproject1.Services;

public interface FileServices {
    boolean createFile(String filename, byte[] fileContent, String folderName);
    boolean createFolder(String folderName);

}
