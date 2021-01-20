package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.dao.AirportRepository;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Airport;
import com.dogukanyildirim.airlinesticketingsystem.exception.ServiceException;
import com.dogukanyildirim.airlinesticketingsystem.exception.ValidationException;
import com.dogukanyildirim.airlinesticketingsystem.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ExceptionMessages.*;

@Service
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    /**
     * Havaalanı kaydetmek için yazılmış servis metotudur.
     *
     * @param airport Havaalanı Entity
     * @return Kaydedilen Havaalanı Entity
     */
    @Override
    public Airport create(Airport airport) {
        if (Objects.isNull(airport)) {
            throw new ServiceException(AIRPORT_OBJECT_MUST_NOT_BE_NULL);
        }
        airportValidation(airport);
        return airportRepository.save(airport);
    }

    /**
     * ID ile bir Havaalanı sorgulamak için yazılmış servis metotudur.
     *
     * @param id Havaalanı ID
     * @return Havaalanı Entity
     */
    @Override
    public Airport read(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Optional<Airport> resultOpt = airportRepository.findById(id);
        if (!resultOpt.isPresent()) {
            throw new ServiceException(AIRPORT_NOT_FOUND);
        }
        return resultOpt.get();
    }

    /**
     * Tüm havaalanlarını sorgulayan metottur.
     *
     * @return Havaalanı listesi
     */
    @Override
    public List<Airport> readAll() {
        return airportRepository.findAll();
    }

    /**
     * Havaalanı güncelleme işlemini yapan metottur.
     *
     * @param airport Havaalanı Entity
     * @return Havaalanı Entity
     */
    @Override
    public Airport update(Airport airport) {
        if (Objects.isNull(airport) || Objects.isNull(airport.getId())) {
            throw new ServiceException(AIRPORT_OBJECT_OR_ID_MUST_NOT_BE_NULL);
        }
        airportValidation(airport);
        return airportRepository.save(airport);
    }

    /**
     * Havaalanı silmek işlemini yapan metottur.
     *
     * @param id Havaalanı id
     * @return Havaalanı Entity
     */
    @Override
    public Airport delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Airport airport = read(id);
        airport.setIsDeleted(true);
        airport = airportRepository.save(airport);

        return airport;
    }

    /**
     * Havaalanı bilgileri validasyon metodudur.
     *
     * @param airport Havaalanı Entity
     * @throws ValidationException Exception nesnesi
     */
    private void airportValidation(Airport airport) throws ValidationException {
        if (airport.getIataCode().length() != 3) {
            throw new ValidationException(IATA_CODE_HAVE_MUST_3_CHAR);
        } else if (airport.getIcaoCode().length() != 4) {
            throw new ValidationException(ICAO_CODE_HAVE_MUST_4_CHAR);
        } else if ((airport.getLatitude() < -90.0) && (airport.getLatitude() > 90.0)) {
            throw new ValidationException(INVALID_LATITUDE_VALUE);
        } else if ((airport.getLongitude() < -180.0) && (airport.getLongitude() > 180.0)) {
            throw new ValidationException(INVALID_LONGITUDE_VALUE);
        } else if ((airport.getAltitude() < 0.0)) {
            throw new ValidationException(INVALID_ALTITUDE_VALUE);
        }

    }
}
