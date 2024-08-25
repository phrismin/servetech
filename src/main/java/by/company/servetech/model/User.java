package by.company.servetech.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotEmpty(message = "Login cannot be empty")
    @Column(name = "login", unique = true, nullable = false)
//    @Size(max = 50, message = "Login cannot be more 50 symbols")
    private String login;

    //TODO регулярное
//    @Pattern(regexp = "(\\?:.*\\d.*\\d.*\\d.*[^0-9\\s].*|.*[^0-9\\s].*\\d.*\\d.*\\d.*)",
//            message = "Password must contain special characters and 3 numbers")
//    @Size(min = 7,  message = "Password cannot be less 7 symbols")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
//    @Size(max = 256,  message = "FullName cannot be more 256 symbols")
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public User() {
    }

    public User(String login, String password, String fullName, Gender gender) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
