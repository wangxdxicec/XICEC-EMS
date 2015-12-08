package com.zhenhappy.ems.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * TDictionary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_dictionary"
    ,schema="dbo"
)

public class TDictionary  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String keyname;
     private String value;
     private String description;


    // Constructors

    /** default constructor */
    public TDictionary() {
    }

    
    /** full constructor */
    public TDictionary(String key, String value, String description) {
        this.keyname = key;
        this.value = value;
        this.description = description;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="keyname", length=500)

    public String getKeyname() {
        return this.keyname;
    }
    
    public void setKeyname(String key) {
        this.keyname = key;
    }
    
    @Column(name="value", length=500)

    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    @Column(name="description", length=2000)

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
   








}