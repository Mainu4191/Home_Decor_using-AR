package com.example.arproject1.Services;

import org.springframework.stereotype.Service;

import com.example.arproject1.utils.FileUtil;



@Service
public class FileServicesImpl implements FileServices {

    private final FileUtil fileUtil;

    public FileServicesImpl(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public boolean createFile(String filename, byte[] fileContent, String folderName) {
        return fileUtil.createFile(filename, fileContent, folderName);

    }

    @Override
    public boolean createFolder(String folderName) {
        return fileUtil.createFolder(folderName);
    }

}
