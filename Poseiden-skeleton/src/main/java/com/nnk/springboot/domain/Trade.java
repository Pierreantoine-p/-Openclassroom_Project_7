package com.nnk.springboot.domain;


import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "trade")
public class Trade {
	
	 @Id
	 @GeneratedValue(strategy= GenerationType.IDENTITY)
	 private Integer tradeId;
	 private String account;
	 private String type;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 private Double buyQuantity;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 private Double sellQuantity;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 private Double buyPrice;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 private Double sellPrice;
	 private String benchmark;
	 private Timestamp tradeDate;
	 private String security;
	 private String status;
	 private String trader;
	 private String book;
	 private String creationName;
	 private Timestamp creationDate;
	 private String revisionName;
	 private Timestamp revisionDate;
	 private String dealName;
	 private String dealType;
	 private String sourceListId;
	 private String side;
}
