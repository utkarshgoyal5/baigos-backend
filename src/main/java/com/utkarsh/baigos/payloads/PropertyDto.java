package com.utkarsh.baigos.payloads;

import com.utkarsh.baigos.entities.Category;
import com.utkarsh.baigos.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PropertyDto {

    private String title;
    private String description;
    private String imageName;
    private CategoryDto category;
    private UserDto user;
    private int categoryId;
    private int userId;
}
