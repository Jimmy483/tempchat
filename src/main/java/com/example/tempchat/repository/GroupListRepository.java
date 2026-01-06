package com.example.tempchat.repository;

import com.example.tempchat.domain.GroupList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupListRepository extends JpaRepository<GroupList, Long> {
}
