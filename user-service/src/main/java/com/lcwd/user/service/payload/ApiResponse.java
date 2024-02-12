package com.lcwd.user.service.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // Builds the object of the current class
public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;

}
