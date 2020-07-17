package com.oriontech.eshopapi.models;
import org.hibernate.annotations.NaturalId;

import com.oriontech.eshopapi.enums.RoleName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 20)
    private RoleName name;

	public Role(RoleName name) {
		this.name = name;
	}
    
    

}
