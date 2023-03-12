package com.utkarsh.baigos.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="property")
@NoArgsConstructor
@Getter
@Setter
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer propertyId;

    @Column(name="title", length = 100, nullable = false)
    private String title;

    @Column(name = "description",  length = 500, nullable = false)
    private String description;
    private String imageName;
    private Date addedDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    private User user;


}
