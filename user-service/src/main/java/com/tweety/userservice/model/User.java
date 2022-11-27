package com.tweety.userservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Node("User")
public class User {
    //@Property
    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String userName;
    @Property
    private String picture;
    @Property
    private String firstName;
    @Property
    private String lastName;
    @Property
    private String email;
    @JsonIgnore
    private String password;
    @Property
    private ZonedDateTime lastTweet;

    //private List<String> tweetsIds;

    //@Relationship(type="FOLLOWS",direction = Relationship.Direction.OUTGOING)
    //private List<User> Followings;

    //@Relationship(type = "FOLLOWS",direction = Relationship.Direction.INCOMING)
    //private List<User> Followers;


}
