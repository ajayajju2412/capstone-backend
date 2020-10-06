package com.upgrad.Eshop.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class UserDTO {
    int id;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Date created;
    String email;
    String firstName;
    String lastName;
    String password;
    String phoneNumber;
    String role;
    String userName;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Date updated;

    //jwtToken
    String jwtToken;
}
