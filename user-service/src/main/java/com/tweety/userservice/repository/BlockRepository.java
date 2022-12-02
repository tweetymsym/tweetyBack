package com.tweety.userservice.repository;

import com.tweety.userservice.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlockRepository extends Neo4jRepository<User,String> {

    @Query("MATCH (CurrentUserId:User ) WHERE CurrentUserId.id=:#{#CurrentUserId} MATCH (UserToBlock:User ) WHERE UserToBlock.id=:#{#UserToBlock} CREATE (CurrentUserId)-[r:Block]->(UserToBlock)")
    Boolean blockUser
            ( @Param("CurrentUserId")
              String CurrentUserId,
              @Param("UserToBlock")
              String UserToBlock);
    @Query("MATCH (CurrentUser:User ) WHERE CurrentUser.id=:#{#UserId} MATCH (CurrentUser) -[r:Block]->(users) RETURN users")
    List<User> getBlocks(@Param("UserId")
                         String UserId  );
    @Query("MATCH (u:User WHERE u.id=:#{#CurrentUserId})-[r:Block]->(p:User WHERE p.id=:#{#UserToUnBlock}) DETACH DELETE r")
    Boolean UnBlockUser(
            @Param("CurrentUserId")
            String CurrentUserId,
            @Param("UserToUnBlock")
            String UserToUnBlock);


}
