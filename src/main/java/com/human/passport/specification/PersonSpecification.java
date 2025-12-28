package com.human.passport.specification;

import com.human.passport.entities.Person;
import com.human.passport.utils.enums.Gender;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PersonSpecification {

    public static Specification<Person> hasFirstName(String firstName) {
        return (root, query, cb) -> firstName == null
                ? null : cb.equal(root.get("firstName"), firstName);
    }

    public static Specification<Person> hasLastName(String lastName) {
        return (root, query, cb) -> lastName == null
                ? null : cb.equal(root.get("lastName"), lastName);
    }

    public static Specification<Person> hasGender(Gender gender) {
        return (root, query, cb) -> gender == null
                ? null : cb.equal(root.get("gender"), gender);
    }

    public static Specification<Person> birthDateBetween(LocalDate start, LocalDate end) {
        return (root, query, cb) -> {
            if (start == null && end == null) return null;
            if (start != null && end != null) return cb.between(root.get("birthDate"), start, end);
            if (start != null) return cb.greaterThanOrEqualTo(root.get("birthDate"), start);
            return cb.lessThanOrEqualTo(root.get("birthDate"), end);
        };
    }

    public static Specification<Person> isActive() {
        return (root, query, cb) -> cb.equal(root.get("active"), true);
    }
}
