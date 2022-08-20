package com.uber.uberapi.models;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass //don't create table for this class
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Auditable implements Serializable{


    @Id //primary key, Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP) //jpa
    @CreatedDate  //hibernate
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate //hibernate
    private Date updateAt;

    @Override
    public int hashCode() {
        return id == null? 0: id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (obj instanceof Auditable) {
            if (id == null || obj == null)
                return false;

            return id == ((Auditable) obj).id;

        } else
            return super.equals(obj);
    }


}

    // database to provide the id
    // autoincrement
    // UUID - 128 bits

    // uuids are long, and comparison is expensive
    // varchar(10)

        // @PrePersist     // creation time - id is assigned
    // @PreUpdate         
    // void updateTimestamp() {
    //     //whenever this entity is persisted this fuction will be called.
    // }

    // objects - compare the memory addresses

    //.equals() - object.equals() ===   