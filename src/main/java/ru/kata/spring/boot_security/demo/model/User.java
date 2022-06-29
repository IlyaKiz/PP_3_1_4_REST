package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Введите ваше имя")
    @Size(min = 2, max = 26, message = "Количество символов в имени должно быть от 2 до 25")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Введите вашу фамилию")
    @Size(min = 1, max = 26, message = "Количество символов в фамилии должно быть от 1 до 25")
    @Column(name = "last_name")
    private String lastName;

    @Min(value = 0)
    @Column(name = "age")
    private int age;

    @NotEmpty(message = "Введите ваш email")
    @Email(message = "email должен быть валидным")
    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @Pattern(regexp = "\\+375\\d{1}\\d{2}\\d{2}\\d{2}",
            message = "Используйте шаблон +375XXXXXXX")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 4)
    private String password;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String firstName, String lastName, int age, String email, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User(String firstName, String lastName, int age, String email, String phoneNumber, String password, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roles = roles;
    }



    public Long getId() {
        return id;
    }



    public String getEmail() {
        return email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        Set<Role> newRoles = new HashSet<>();
        for (Role otherRole : roles) {
            Role role = new Role();
            if (otherRole.getName().startsWith("ROLE_")) {
                role.setId(otherRole.getId());
                role.setName(otherRole.getName().substring(5));
            }
            newRoles.add(role);
        }
        return newRoles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email)
                && Objects.equals(phoneNumber, user.phoneNumber)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, email, phoneNumber, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}
