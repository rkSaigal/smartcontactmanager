package com.smart.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTACT")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cId;
    private String name;
    private String secondName;
    private String work;
    private String email;
    private String phone;
    private String image;
    @Lob
    private String description;

    @ManyToOne
    @JsonIgnore
    private User user;


    @Override
    public boolean equals(Object obj) {
        return this.cId==((Contact)obj).getCId();
    }
}
