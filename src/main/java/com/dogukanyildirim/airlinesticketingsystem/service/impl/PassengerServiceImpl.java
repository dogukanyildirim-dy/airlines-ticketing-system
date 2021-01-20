package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.dao.PassengerRepository;
import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.Passenger;
import com.dogukanyildirim.airlinesticketingsystem.exception.ServiceException;
import com.dogukanyildirim.airlinesticketingsystem.service.PassengerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ExceptionMessages.*;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    /**
     * Yolcu kaydetmek için yazılmış servis metotudur.
     *
     * @param passenger Yolcu Entity
     * @return Kaydedilen Yolcu Entity
     */
    @Override
    public Passenger create(Passenger passenger) {
        if (Objects.isNull(passenger)) {
            throw new ServiceException(PASSENGER_OBJECT_MUST_NOT_BE_NULL);
        }
        return passengerRepository.save(passenger);
    }

    /**
     * ID ile bir Yolcu sorgulamak için yazılmış servis metotudur.
     *
     * @param id Yolcu ID
     * @return Yolcu Entity
     */
    @Override
    public Passenger read(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Optional<Passenger> resultOpt = passengerRepository.findById(id);
        if (!resultOpt.isPresent()) {
            throw new ServiceException(PASSENGER_NOT_FOUND);
        }
        return resultOpt.get();
    }

    /**
     * Tüm yolcuları sorgulayan metottur.
     *
     * @return Yolcu listesi
     */
    @Override
    public List<Passenger> readAll() {
        return passengerRepository.findAll();
    }

    /**
     * Yolcu güncelleme işlemini yapan metottur.
     *
     * @param passenger Yolcu Entity
     * @return Yolcu Entity
     */
    @Override
    public Passenger update(Passenger passenger) {
        if (Objects.isNull(passenger) || Objects.isNull(passenger.getId())) {
            throw new ServiceException(PASSENGER_OBJECT_OR_ID_MUST_NOT_BE_NULL);
        }
        return passengerRepository.save(passenger);
    }

    /**
     * Yolcu silmek işlemini yapan metottur.
     *
     * @param id Yolcu id
     * @return Yolcu Entity
     */
    @Override
    public Passenger delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Passenger passenger = read(id);
        passenger.setIsDeleted(true);
        passenger = passengerRepository.save(passenger);

        return passenger;
    }
}
