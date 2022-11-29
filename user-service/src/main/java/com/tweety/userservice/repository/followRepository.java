package com.tweety.userservice.repository;

import com.tweety.userservice.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface followRepository extends Neo4jRepository<User,Long> {


    @Query("MATCH (CurrentUserId:User ) WHERE id(CurrentUserId)=:#{#CurrentUserId} MATCH (UserToFollow:User ) WHERE id(UserToFollow)=:#{#UserToFollow} CREATE (CurrentUserId)-[r:FOLLOWS]->(UserToFollow)")
    Boolean followUser(
            @Param("CurrentUserId")
            Long CurrentUserId,
            @Param("UserToFollow")
            Long UserToFollow);
    @Query("MATCH (u:User WHERE id(u)=:#{#CurrentUserId})-[r:FOLLOWS]->(p:User WHERE id(p)=:#{#UserToUnFollow}) DETACH DELETE r")
    Boolean UnfollowUser(
            @Param("CurrentUserId")
            Long CurrentUserId,
            @Param("UserToUnFollow")
            Long UserToUnFollow);

    @Query("MATCH (u:User WHERE id(u)=:#{#CurrentUserId})<-[r:FOLLOWS]-(p:User WHERE id(p)=:#{#UserToRemove}) DETACH DELETE r")
    Boolean removeFromMyFollowers(
            @Param("CurrentUserId")
            Long CurrentUserId,
            @Param("UserToRemove")
            Long UserToRemove);

    @Query("MATCH (CurrentUser:User ) WHERE id(CurrentUser)=:#{#UserId} MATCH (CurrentUser) -[r:FOLLOWS]->(users) RETURN users")
    List<User> getFollowings(@Param("UserId")
                             Long UserId  );

    @Query("MATCH (CurrentUser:User ) WHERE id(CurrentUser)=:#{#UserId} MATCH (CurrentUser) <-[r:FOLLOWS]-(users) RETURN users")
    List<User> getFollowers(
            @Param("UserId")
            Long UserId );

}
