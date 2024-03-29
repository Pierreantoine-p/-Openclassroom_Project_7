package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rulename")
@Data
public class RuleName {

   
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String json;
    private String template;
    
    @Column(name = "sqlstr")
    private String sqlStr;
    
    @Column(name = "sqlpart")
    private String sqlPart;
}
