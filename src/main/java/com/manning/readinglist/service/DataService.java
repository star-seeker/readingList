package com.manning.readinglist.service;

import com.manning.readinglist.entity.Reader;
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
}
