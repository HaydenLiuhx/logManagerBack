package com.hayden.airit.service.impl;

import com.hayden.airit.Dao.LogfileDAO;
import com.hayden.airit.entity.Logfile;
import com.hayden.airit.service.LogfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogfileServiceImpl implements LogfileService {
    @Autowired
    LogfileDAO logfileDAO;

    @Override
    public void save(Logfile logfile) {
        logfileDAO.save(logfile);
    }

    @Override
    public List<Logfile> getLogfileByUserId(Integer userId) {
        return null;
    }
}
