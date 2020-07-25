package com.hayden.airit.service.impl;

import com.hayden.airit.Dao.AdminDAO;
import com.hayden.airit.service.AdminService;
import com.hayden.airit.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDAO adminDAO;

    @Override
    public Admin findAdminById(Integer id) {
        return adminDAO.getOne(id);
    }

    @Override
    public Admin findAdminByUsername(String username) {
        return adminDAO.findAdminByUsername(username);
    }

    @Override
    public List<Admin> getAll() {
        return adminDAO.findAll();
    }
}
