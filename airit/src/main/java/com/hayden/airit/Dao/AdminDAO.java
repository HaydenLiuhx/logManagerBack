package com.hayden.airit.Dao;


import com.hayden.airit.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AdminDAO extends JpaRepository<Admin, Integer> {
    Admin findAdminById(Integer id);
    Admin findAdminByUsername(String username);

}
