package com.example.studentmanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentDto {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Branch is required")
    private String branch;

    @NotNull(message = "Year of passing is required")
    @Min(1950)
    @Max(2030)
    private Integer yop;

    @NotNull(message = "Active status is required")
    private Boolean active;
}
