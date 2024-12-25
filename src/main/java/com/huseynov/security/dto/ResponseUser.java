package com.huseynov.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    Long id;
    String username;
    String errorMessage;

}
