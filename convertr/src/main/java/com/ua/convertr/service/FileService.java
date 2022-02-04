package com.ua.convertr.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    void save(MultipartFile miltipartFile);

    public Resource convert(String miltipartFileName);

}
