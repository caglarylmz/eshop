package com.oriontech.eshopapi.payloads.req;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UpdateCategoryRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String status;
}
  