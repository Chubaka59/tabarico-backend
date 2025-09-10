package com.gtarp.tabaricobackend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gtarp.tabaricobackend.dto.UserDto;
import com.gtarp.tabaricobackend.dto.accounting.DashboardDto;
import com.gtarp.tabaricobackend.entities.accounting.CustomerSale;
import com.gtarp.tabaricobackend.entities.accounting.ExporterSale;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CustomerSale> customerSales = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ExporterSale> exporterSales = new ArrayList<>();

    public User(UserDto userDto) {
        this.username = userDto.getUsername();
        this.password = new BCryptPasswordEncoder().encode(userDto.getPassword());
        this.lastName = userDto.getLastName();
        this.firstName = userDto.getFirstName();
        this.phone = userDto.getPhone();
        this.role = userDto.getRole();
    }

    public User update(UserDto userDto) {
        this.username = userDto.getUsername();
        if(userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            this.password = new BCryptPasswordEncoder().encode(userDto.getPassword());
        }
        this.lastName = userDto.getLastName();
        this.firstName = userDto.getFirstName();
        this.phone = userDto.getPhone();
        this.role = userDto.getRole();
        return this;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    public User updatePassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
        return this;
    }

    public User updateFromDashboard(DashboardDto dashboardDto) {
        this.quota = dashboardDto.isQuota();
        this.exporterQuota = dashboardDto.isExporterQuota();
        if (dashboardDto.isHoliday() && dashboardDto.getEndOfHoliday() != null && dashboardDto.getEndOfHoliday().isAfter(LocalDate.now())) {
            this.holiday = dashboardDto.isHoliday();
            this.endOfHoliday = dashboardDto.getEndOfHoliday();
        } else {
            this.holiday = false;
            this.endOfHoliday = null;
        }
        this.warning1 = dashboardDto.isWarning1();
        this.warning2 = dashboardDto.isWarning2();
        return this;
    }
}
