package com.course5i.base.framework.service;

import java.util.List;

import com.course5i.base.framework.entity.UserDetailsEntity;

public interface UserDetailsService {

	public List<UserDetailsEntity> findAll();
}
