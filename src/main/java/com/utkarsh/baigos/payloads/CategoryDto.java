package com.utkarsh.baigos.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private int categoryId;
    @NotEmpty
    @Size(min = 4, message = "title must have at least 4 character")
    private String title;
    @NotEmpty
    private String description;
}
