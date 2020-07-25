package com.hayden.airit.Dao;

import com.hayden.airit.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerDAO extends JpaRepository<Manager, Integer> {
    Manager findManagerById(Integer id);
    Manager findManagerByUsername(String username);

    @Override
    List<Manager> findAll();
}
