package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.*;

@Entity
@Table(name = "recipes")
public class Recipe extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipieid;

    @NotNull
    private String recipename;

    private String description;

    @NotNull
    private TextArea directions;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "Re")


}
