package org.cool.zoo.entities.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.cool.zoo.entities.Branch;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Dang Dim
 * Date     : 05-Jan-18, 8:59 AM
 * Email    : d.dim@gl-f.com
 */

@Entity
@Table(name = "tbl_staff")
@Where(clause = "is_deleted='false'")
public class Staff {

    private Long id;
    private String loginId;
    private String username;
    private String password;
    private String mobile;
    private String email;
    private boolean isDeleted;
    private Date createdDate;
    private Date updatedDate;
    private Long createdBy;
    private Long updatedBy;
    private Set<Role> authorities;
    private Branch branch;
    private List<Dealer>dealers;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "login_id")
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Column(name = "name", nullable = false, length = 250, unique = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Size(min = 0, max = 500)
    @Column(name = "password", nullable = false)
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email
    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tbl_staff_role", joinColumns = {
            @JoinColumn(name = "staff_id", nullable = false, updatable = true)},
            inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = true)})
    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (!username.equals(staff.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_dtm")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_dtm")
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Column(name = "created_by ")
    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "updated_by ")
    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "is_deleted", columnDefinition="BIT DEFAULT 0", nullable = false)
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tbl_staff_dealer", joinColumns = {
            @JoinColumn(name = "staff_id", nullable = false, updatable = true)},
            inverseJoinColumns = {@JoinColumn(name = "dealer_id", nullable = false, updatable = true)})
    public List<Dealer> getDealers() {
        return dealers;
    }

    public void setDealers(List<Dealer> dealers) {
        this.dealers = dealers;
    }

}
