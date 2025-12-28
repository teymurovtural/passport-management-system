package com.human.passport.services.impl;


import com.human.passport.dto.request.PassportRequestDto;
import com.human.passport.dto.response.PassportResponseDto;
import com.human.passport.entities.Passport;
import com.human.passport.exception.IdNotFoundException;
import com.human.passport.mapper.PassportMapper;
import com.human.passport.repositories.PassportRepository;
import com.human.passport.repositories.PersonRepository;
import com.human.passport.services.PassportService;
import com.human.passport.specification.PassportSpecification;
import com.human.passport.utils.enums.PassportType;
import com.human.passport.utils.helpers.RelationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.human.passport.specification.PassportSpecification.*;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;
    private final PersonRepository personRepository;
    private final PassportMapper passportMapper;

    @Override
    @Transactional
    public PassportResponseDto addNewPassport(PassportRequestDto dto) {
        Passport passport = passportMapper.dtoToEntity(dto);
        RelationUtils.linkPersonToPassport(dto.getPersonId(), passport, personRepository);
        return passportMapper.entityToDto(passportRepository.save(passport));
    }

    @Override
    @Transactional
    public PassportResponseDto updatePassport(Long id, PassportRequestDto dto) {
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Passport not found with id: " + id));

        passport.setSerialNumber(dto.getSerialNumber());
        passport.setPassportNumber(dto.getPassportNumber());
        passport.setIssuingCountry(dto.getIssuingCountry());
        passport.setIssueDate(dto.getIssueDate());
        passport.setExpireDate(dto.getExpireDate());
        passport.setPassportType(dto.getPassportType());

        RelationUtils.linkPersonToPassport(dto.getPersonId(), passport, personRepository);

        return passportMapper.entityToDto(passportRepository.save(passport));
    }

    @Override
    @Transactional
    public void deletePassport(Long id) {
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Passport not found with id: " + id));
        passportRepository.delete(passport);
    }

    @Override
    public PassportResponseDto getPassportById(Long id) {
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Passport not found with id: " + id));
        return passportMapper.entityToDto(passport);
    }

    @Override
    public List<PassportResponseDto> getAllPassports() {
        return passportRepository.findAll().stream()
                .map(passportMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PassportResponseDto> searchPassports(String serialNumber, PassportType type, Long personId) {
        return passportRepository.findAll(
                        Specification.where(hasSerialNumber(serialNumber))
                                .and(hasPassportType(type))
                                .and(hasPersonId(personId))
                )
                .stream()
                .map(passportMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSoftPassport(Long id) {
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Passport not found with id: " + id));
        passport.setActive(false);
        passportRepository.save(passport);
    }

    @Override
    public Page<PassportResponseDto> searchPassports(
            String serialNumber,
            PassportType passportType,
            Long personId,
            LocalDate issueStart,
            LocalDate issueEnd,
            LocalDate expireStart,
            LocalDate expireEnd,
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<Passport> passportsPage = passportRepository.findAll(
                Specification.where(PassportSpecification.hasSerialNumber(serialNumber))
                        .and(PassportSpecification.hasPassportType(passportType))
                        .and(PassportSpecification.hasPersonId(personId))
                        .and(PassportSpecification.issueDateBetween(issueStart, issueEnd))
                        .and(PassportSpecification.expireDateBetween(expireStart, expireEnd)),
                pageRequest
        );

        return passportsPage.map(passportMapper::entityToDto);
    }

}

