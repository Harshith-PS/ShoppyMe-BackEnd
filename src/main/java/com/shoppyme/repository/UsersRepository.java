package com.shoppyme.repository;

import org.springframework.data.repository.CrudRepository;

import com.shoppyme.entity.Users;

public interface UsersRepository extends CrudRepository<Users, String> {

}
