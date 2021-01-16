package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.dao.AirlineCompanyRepository;
import com.dogukanyildirim.airlinesticketingsystem.domain.AirlineCompany;
import com.dogukanyildirim.airlinesticketingsystem.exception.ServiceException;
import com.dogukanyildirim.airlinesticketingsystem.service.AirlineCompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ExceptionMessages.*;

@Service
public class AirlineCompanyServiceImpl implements AirlineCompanyService {

    private final AirlineCompanyRepository airlineCompanyRepository;

    public AirlineCompanyServiceImpl(AirlineCompanyRepository airlineCompanyRepository) {
        this.airlineCompanyRepository = airlineCompanyRepository;
    }

    @Override
    public AirlineCompany create(AirlineCompany airlineCompany) {
        if (Objects.isNull(airlineCompany)) {
            throw new ServiceException(AIRLINE_COMPANY_OBJECT_MUST_NOT_BE_NULL);
        }
        return airlineCompanyRepository.save(airlineCompany);
    }

    @Override
    public AirlineCompany read(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Optional<AirlineCompany> resultOpt = airlineCompanyRepository.findById(id);
        if (!resultOpt.isPresent()) {
            throw new ServiceException(AIRLINE_COMPANY_NOT_FOUND);
        }
        return resultOpt.get();
    }

    @Override
    public List<AirlineCompany> readAll() {
        return airlineCompanyRepository.findAll();
    }

    @Override
    public AirlineCompany update(AirlineCompany airlineCompany) {
        if (Objects.isNull(airlineCompany) || Objects.isNull(airlineCompany.getId())) {
            throw new ServiceException(AIRLINE_COMPANY_OBJECT_OR_ID_MUST_NOT_BE_NULL);
        }
        return airlineCompanyRepository.save(airlineCompany);
    }

    @Override
    public AirlineCompany delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        AirlineCompany airlineCompany = read(id);
        airlineCompany.setIsDeleted(true);
        airlineCompany = airlineCompanyRepository.save(airlineCompany);

        return airlineCompany;
    }

}
