package com.example.tempchat.repository;

import com.example.tempchat.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

//    List<Message> findByGroupCode(String groupCode);

    List<Message> findAllByGroupList_Id(Long id);
}
