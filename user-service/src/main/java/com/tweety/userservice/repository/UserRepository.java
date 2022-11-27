package com.tweety.userservice.repository;

import com.tweety.userservice.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User,Long> {




}
