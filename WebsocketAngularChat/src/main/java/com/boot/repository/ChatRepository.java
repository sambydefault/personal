package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.model.Message;

@Repository
public interface ChatRepository extends JpaRepository<Message, Long> {

}
