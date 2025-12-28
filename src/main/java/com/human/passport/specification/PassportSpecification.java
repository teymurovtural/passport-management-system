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
            if (start != null && end != null) return cb.between(root.get("issueDate"), start, end);
            if (start != null) return cb.greaterThanOrEqualTo(root.get("issueDate"), start);
            return cb.lessThanOrEqualTo(root.get("issueDate"), end);
        };
    }

    public static Specification<Passport> expireDateBetween(LocalDate start, LocalDate end) {
        return (root, query, cb) -> {
            if (start == null && end == null) return null;
            if (start != null && end != null) return cb.between(root.get("expireDate"), start, end);
            if (start != null) return cb.greaterThanOrEqualTo(root.get("expireDate"), start);
            return cb.lessThanOrEqualTo(root.get("expireDate"), end);
        };
    }

    public static Specification<Passport> isActive() {
        return (root, query, cb) -> cb.equal(root.get("active"), true);
    }

}
