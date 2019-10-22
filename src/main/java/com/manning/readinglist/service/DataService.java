package com.manning.readinglist.service;

import com.manning.readinglist.entity.Reader;
import java.io.File;
import org.springframework.stereotype.Service;


@Service
public class DataService {

    public Reader retrieveReader(String id) {
        // throw new ReaderNotFoundException("not fount reader.");
        Reader reader = new Reader();
        reader.setId(id);
        reader.setUsername("夜影");
        return reader;
    }

    public static void main(String[] args) {
        String proFilePath = System.getProperty("user.dir");
        proFilePath += File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator
                + "static" + File.separator + "style.css";
        System.out.println(proFilePath);
    }
}
