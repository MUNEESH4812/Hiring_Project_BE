// UpdateStatusRequest.java
package com.example.onboarding.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpdateStatusRequest {

    @NotBlank(message = "Status cannot be blank")
    @Pattern(
        regexp = "APPLIED|INTERVIEWED|OFFERED|ONBOARDED",
        message = "Status must be one of: APPLIED, INTERVIEWED, OFFERED,ONBOARDED"
    )
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
