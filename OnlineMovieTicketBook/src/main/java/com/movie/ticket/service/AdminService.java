package com.movie.ticket.service;

import java.util.List;

import com.movie.ticket.model.Admin;

public interface AdminService {

	Admin addAdmin(Admin admin);

	List<Admin> getAllAdmin();

}
