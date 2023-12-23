package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_ID ;
	@Column(name = "USERNAME")
    private String username;
	@Column(name = "PASSWORD")
    private String pasword ;
	@Column(name = "PRIVILEGES")
	private String privleges;
	
	
	public Account() {
		super();
	}

	public Account(String username, String pasword, String privleges) {
		super();
		this.username = username;
		this.pasword = pasword;
		this.privleges = privleges;
	}

	public Long getAccount_ID() {
		return account_ID;
	}

	public void setAccount_ID(Long account_ID) {
		this.account_ID = account_ID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public String getPrivleges() {
		return privleges;
	}

	public void setPrivleges(String privleges) {
		this.privleges = privleges;
	}
	@PrePersist
    public void prePersist() {
        // Kiểm tra và đặt giá trị password nếu nó là null hoặc chuỗi trắng
        if (this.pasword == null || this.pasword.trim().isEmpty()) {
            this.pasword = "defaultPassword"; // Đặt giá trị mặc định hoặc xử lý theo logic của bạn
        }
     // Kiểm tra và đặt giá trị privileges nếu nó là null hoặc chuỗi trắng
        if (this.privleges == null || this.privleges.trim().isEmpty()) {
            this.privleges = "defaultPrivileges"; // Đặt giá trị mặc định hoặc xử lý theo logic của bạn
        }
    }
}
