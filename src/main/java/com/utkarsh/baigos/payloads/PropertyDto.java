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

    private Integer propertyId;
    private String title;
    private String description;
    private String imageName;
    private Date addedDate;
    private Category category;
    private User user;
}
