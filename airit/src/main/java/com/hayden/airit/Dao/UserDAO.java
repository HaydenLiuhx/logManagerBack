package com.hayden.airit.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hayden.airit.entity.User;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    User findUserByName(String name);
    User findUserByUsername(String username);
    List<User> findAll();
    List<User> findUserByManager_Id(Integer managerId);
}
