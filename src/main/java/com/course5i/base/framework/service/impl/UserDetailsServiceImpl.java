package com.course5i.base.framework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course5i.base.framework.entity.UserDetailsEntity;
import com.course5i.base.framework.repository.UserDetailsRepository;
import com.course5i.base.framework.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Override
	public List<UserDetailsEntity> findAll() {
		return userDetailsRepository.findAll();
	}
}
