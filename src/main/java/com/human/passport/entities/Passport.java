package com.human.passport.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.human.passport.utils.enums.PassportType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "passport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;

    @Column(name = "passport_number", nullable = false, unique = true)
    private String passportNumber;

    @Column(name = "issuing_country", nullable = false)
    private String issuingCountry;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "expire_date", nullable = false)
    private LocalDate expireDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "passport_type", nullable = false)
    private PassportType passportType;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Owning side
    @OneToOne(optional = false)
    @JsonBackReference
    @JoinColumn(
            name = "person_id",
            nullable = false,
            unique = true
    )
    private Person person;
}
