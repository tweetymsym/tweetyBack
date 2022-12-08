package com.tweety.userservice.repository;

import com.tweety.userservice.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FollowRepository extends Neo4jRepository<User,String> {


    @Query("MATCH (CurrentUserId:User ) WHERE CurrentUserId.id=:#{#CurrentUserId} MATCH (UserToFollow:User ) WHERE UserToFollow.id=:#{#UserToFollow} CREATE (CurrentUserId)-[r:FOLLOWS]->(UserToFollow)")
    Boolean followUser(
            @Param("CurrentUserId")
            String CurrentUserId,
            @Param("UserToFollow")
            String UserToFollow);
    @Query("MATCH (u:User WHERE u.id=:#{#CurrentUserId})-[r:FOLLOWS]->(p:User WHERE p.id=:#{#UserToUnFollow}) DETACH DELETE r")
    Boolean UnfollowUser(
            @Param("CurrentUserId")
            String CurrentUserId,
            @Param("UserToUnFollow")
            String UserToUnFollow);

    @Query("MATCH (u:User WHERE u.id=:#{#CurrentUserId})<-[r:FOLLOWS]-(p:User WHERE p.id=:#{#UserToRemove}) DETACH DELETE r")
    Boolean removeFromMyFollowers(
            @Param("CurrentUserId")
            String CurrentUserId,
            @Param("UserToRemove")
            String UserToRemove);

    @Query("MATCH (CurrentUser:User ) WHERE CurrentUser.id=:#{#UserId} MATCH (CurrentUser) -[r:FOLLOWS]->(users) RETURN users")
    List<User> getFollowings(@Param("UserId")
                             String UserId  );

    @Query("MATCH (CurrentUser:User ) WHERE CurrentUser.id=:#{#UserId} MATCH (CurrentUser) <-[r:FOLLOWS]-(users) RETURN users")
    List<User> getFollowers(
            @Param("UserId")
            String UserId );

}
