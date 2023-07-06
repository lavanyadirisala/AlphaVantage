package com.stock.AlphaVantageAPI.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "stocksData")
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)

public class APIData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(columnDefinition = "jsonb") 
//    //@Type(type = "jsonb", value = null)
//    private String data;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "data")
    private Object data;

}
