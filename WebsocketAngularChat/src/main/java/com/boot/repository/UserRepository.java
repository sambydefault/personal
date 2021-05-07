package com.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM USER user where user.user_name = :username", nativeQuery = true)
	public User findExistingUser(@Param("username") String username);

	@Query(value = "SELECT * FROM USER user where user.user_status = :status", nativeQuery = true)
	public List<User> findActiveUsers(@Param("status") String status);

}
