package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails, Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "email",unique = true, nullable = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

//    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
//        return  roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(String firstName, String lastName, int age, String email, String password, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(String firstName, String lastName, int age, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public User() {
    }


    public String getRolesString() {
        StringBuilder sb = new StringBuilder();
        for (Role role:roles) {
            if(role.getName().contains("ROLE_ADMIN")) {
                sb.append(" ADMIN");
            } else if (role.getName().contains("ROLE_USER")) {
                sb.append(" USER");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", roles=" + getRolesString() +
                '}';
    }
}