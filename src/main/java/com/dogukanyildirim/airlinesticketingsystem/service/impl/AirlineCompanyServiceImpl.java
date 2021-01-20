package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.dao.AirlineCompanyRepository;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.AirlineCompany;
import com.dogukanyildirim.airlinesticketingsystem.exception.ServiceException;
import com.dogukanyildirim.airlinesticketingsystem.exception.ValidationException;
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

    /**
     * Havayolu şirketi kaydetmek için yazılmış servis metotudur.
     *
     * @param airlineCompany Havayolu Şirketi Entity
     * @return Kaydedilen Havayolu Şirketi Entity
     */
    @Override
    public AirlineCompany create(AirlineCompany airlineCompany) {
        if (Objects.isNull(airlineCompany)) {
            throw new ServiceException(AIRLINE_COMPANY_OBJECT_MUST_NOT_BE_NULL);
        }
        airlineCompanyValidation(airlineCompany);
        return airlineCompanyRepository.save(airlineCompany);
    }

    /**
     * ID ile bir Havayolu Şirketi sorgulamak için yazılmış servis metotudur.
     *
     * @param id Havayolu Şirketi ID
     * @return Havayolu Şirketi Entity
     */
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

    /**
     * Tüm havayolu şirketlerini sorgulayan metottur.
     *
     * @return Havayolu şirketi listesi
     */
    @Override
    public List<AirlineCompany> readAll() {
        return airlineCompanyRepository.findAll();
    }

    /**
     * Havayolu şirketi güncelleme işlemini yapan metottur.
     *
     * @param airlineCompany Havayolu şirketi Entity
     * @return Havayolu şirketi Entity
     */
    @Override
    public AirlineCompany update(AirlineCompany airlineCompany) {
        if (Objects.isNull(airlineCompany) || Objects.isNull(airlineCompany.getId())) {
            throw new ServiceException(AIRLINE_COMPANY_OBJECT_OR_ID_MUST_NOT_BE_NULL);
        }
        airlineCompanyValidation(airlineCompany);
        return airlineCompanyRepository.save(airlineCompany);
    }

    /**
     * Havayolu şirketi silmek işlemini yapan metottur.
     *
     * @param id Havayolu şirketi id
     * @return Havayolu şirketi Entity
     */
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

    /**
     * Havayolu şirketi bilgileri validasyon metodudur.
     *
     * @param airlineCompany Havayolu şirketi Entity
     * @throws ValidationException Exception nesnesi
     */
    private void airlineCompanyValidation(AirlineCompany airlineCompany) throws ValidationException {
        if (airlineCompany.getIataCode().length() != 2) {
            throw new ValidationException(IATA_CODE_HAVE_MUST_2_CHAR);
        } else if (airlineCompany.getIcaoCode().length() != 3) {
            throw new ValidationException(ICAO_CODE_HAVE_MUST_3_CHAR);
        }
    }

}
