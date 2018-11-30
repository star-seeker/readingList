package com.manning.readinglist.service;

import com.manning.readinglist.entity.Reader;
import com.manning.readinglist.exception.ReaderNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    public Reader retrieveReader() {
        // throw new ReaderNotFoundException("not fount reader.");
        Reader reader = new Reader();
        reader.setUsername("夜影");
        return reader;
    }
}
