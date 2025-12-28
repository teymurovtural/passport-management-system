package com.human.passport.specification;

import com.human.passport.entities.Passport;
import com.human.passport.utils.enums.PassportType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PassportSpecification {

    public static Specification<Passport> hasSerialNumber(String serialNumber) {
        return (root, query, cb) -> serialNumber == null ? null : cb.equal(root.get("serialNumber"), serialNumber);
    }

    public static Specification<Passport> hasPassportType(PassportType type) {
        return (root, query, cb) -> type == null ? null : cb.equal(root.get("passportType"), type);
    }

    public static Specification<Passport> hasPersonId(Long personId) {
        return (root, query, cb) -> personId == null ? null : cb.equal(root.get("person").get("id"), personId);
    }

    public static Specification<Passport> issueDateBetween(LocalDate start, LocalDate end) {
        return (root, query, cb) -> {
            if (start == null && end == null) return null;
            if (start != null && end != null) return cb.between(root.get("birthDate"), start, end);
            if (start != null) return cb.greaterThanOrEqualTo(root.get("birthDate"), start);
            return cb.lessThanOrEqualTo(root.get("birthDate"), end);
        };

    }

    public static Specification<Passport> expireDateBetween(LocalDate start, LocalDate end) {
        return (root, query, cb) -> {
            if (start == null && end == null) return null;
            if (start != null && end != null) return cb.between(root.get("birthDate"), start, end);
            if (start != null) return cb.greaterThanOrEqualTo(root.get("birthDate"), start);
            return cb.lessThanOrEqualTo(root.get("birthDate"), end);
        };
    }

}
