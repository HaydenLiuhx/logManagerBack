package com.hayden.airit.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.hayden.airit.entity.Admin;


public interface AdminService {
    public Admin findAdminById(Integer id);
    public Admin findAdminByUsername(String username);
    List<Admin> getAll();
}
