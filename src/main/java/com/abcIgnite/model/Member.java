package com.abcIgnite.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Schema(hidden = true)
	    private Long id;

	    @Column(nullable = false, unique = true)
	    private String name;

	    @Column(nullable = false, unique = true)
	    private String email;

	    
		public Member() {
			super();
		}

		public Member(Long id, String name, String email) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	    

}
