package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.dao.AirplaneRepository;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Airplane;
import com.dogukanyildirim.airlinesticketingsystem.exception.ServiceException;
import com.dogukanyildirim.airlinesticketingsystem.exception.ValidationException;
import com.dogukanyildirim.airlinesticketingsystem.service.AirplaneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ExceptionMessages.*;

@Service
public class AirplaneServiceImpl implements AirplaneService {
    private final AirplaneRepository airplaneRepository;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    /**
     * Uçak kaydetmek için yazılmış servis metotudur.
     *
     * @param airplane Uçak Entity
     * @return Kaydedilen Uçak Entity
     */
    @Override
    public Airplane create(Airplane airplane) {
        if (Objects.isNull(airplane)) {
            throw new ServiceException(AIRPLANE_OBJECT_MUST_NOT_BE_NULL);
        }
        airplaneValidation(airplane);
        return airplaneRepository.save(airplane);
    }

    /**
     * ID ile bir Uçak sorgulamak için yazılmış servis metotudur.
     *
     * @param id Uçak ID
     * @return Uçak Entity
     */
    @Override
    public Airplane read(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Optional<Airplane> resultOpt = airplaneRepository.findById(id);
        if (!resultOpt.isPresent()) {
            throw new ServiceException(AIRPLANE_NOT_FOUND);
        }
        return resultOpt.get();
    }

    /**
     * Tüm uçakları sorgulayan metottur.
     *
     * @return Uçak listesi
     */
    @Override
    public List<Airplane> readAll() {
        return airplaneRepository.findAll();
    }

    /**
     * Uçak güncelleme işlemini yapan metottur.
     *
     * @param airplane Uçak Entity
     * @return Uçak Entity
     */
    @Override
    public Airplane update(Airplane airplane) {
        if (Objects.isNull(airplane) || Objects.isNull(airplane.getId())) {
            throw new ServiceException(AIRPLANE_OBJECT_OR_ID_MUST_NOT_BE_NULL);
        }
        airplaneValidation(airplane);
        return airplaneRepository.save(airplane);
    }

    /**
     * Uçak silmek işlemini yapan metottur.
     *
     * @param id Uçak id
     * @return Uçak Entity
     */
    @Override
    public Airplane delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Airplane airplane = read(id);
        airplane.setIsDeleted(true);
        airplane = airplaneRepository.save(airplane);

        return airplane;
    }

    /**
     * Uçak bilgileri validasyon metodudur.
     *
     * @param airplane Uçak Entity
     * @throws ValidationException Exception nesnesi
     */
    private void airplaneValidation(Airplane airplane) throws ValidationException {
        if (airplane.getIataCode().length() != 3) {
            throw new ValidationException(IATA_CODE_HAVE_MUST_3_CHAR);
        } else if (airplane.getIcaoCode().length() != 4) {
            throw new ValidationException(ICAO_CODE_HAVE_MUST_4_CHAR);
        } else if (Objects.isNull(airplane.getNumberOfSeats()) || airplane.getNumberOfSeats() <= 0) {
            throw new ValidationException(INVALID_NUMBER_OF_SEATS_VALUE);
        }
    }
}
