package com.tweety.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

@Getter
@Setter
public class UserInListDto {
    private String id;
    private String userName;
    private String picture;
    private String firstName;
    private String lastName;
}
