package com.tweety.userservice.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Node("User")
public class User {
    //@Property
    @Id
    private String id;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    @Property
    private Instant lastTweet;

    //private List<String> tweetsIds;

    //@Relationship(type="FOLLOWS",direction = Relationship.Direction.OUTGOING)
    //private List<User> Followings;

    //@Relationship(type = "FOLLOWS",direction = Relationship.Direction.INCOMING)
    //private List<User> Followers;


}
