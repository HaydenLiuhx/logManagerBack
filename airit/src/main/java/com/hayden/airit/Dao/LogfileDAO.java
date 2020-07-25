package com.hayden.airit.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hayden.airit.entity.Logfile;

import java.util.Date;
import java.util.List;

public interface LogfileDAO extends JpaRepository<Logfile, Integer> {
    Logfile findLogfileById(Integer id);
    List<Logfile> findLogfileByUser_Id(Integer userId);
    Logfile findLogfileByLogTime(Date logtime);
    List<Logfile> findAll();

}
