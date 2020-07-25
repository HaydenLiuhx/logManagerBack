package com.hayden.airit.service;

import com.hayden.airit.entity.Logfile;

import java.util.List;

public interface LogfileService {
    void save(Logfile logfile);
    List<Logfile> getLogfileByUserId(Integer userId);
}
