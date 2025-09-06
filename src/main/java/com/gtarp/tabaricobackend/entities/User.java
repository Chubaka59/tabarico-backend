package com.gtarp.tabaricobackend.entities;

import com.gtarp.tabaricobackend.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UpdatableEntity<User, UserDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;
    @NotBlank
    private String phone;
    @ManyToOne
    @JoinColumn(name = "role_id")
    @NotNull
    private Role role;
    private boolean holiday = false;
    private LocalDate endOfHoliday;
    private boolean warning1 = false;
    private boolean warning2 = false;
    private Integer cleanMoneySalary;
    private Integer dirtyMoneySalary;
    private Integer cleanMoneySalaryPreviousWeek;
    private Integer dirtyMoneySalaryPreviousWeek;
    private boolean quota = false;
    private boolean exporterQuota = false;
    private String identityCardImage;

    public User(UserDto userDto) {
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.lastName = userDto.getLastName();
        this.firstName = userDto.getFirstName();
        this.phone = userDto.getPhone();
        this.role = userDto.getRole();
    }

    public User update(UserDto userDto) {
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.lastName = userDto.getLastName();
        this.firstName = userDto.getFirstName();
        this.phone = userDto.getPhone();
        this.role = userDto.getRole();
        return this;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    public User updatePassword(UserDto userDto) {
        this.password = userDto.getPassword();
        return this;
    }
}
