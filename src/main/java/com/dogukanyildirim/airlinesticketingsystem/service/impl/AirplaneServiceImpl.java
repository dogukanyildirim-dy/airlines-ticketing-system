package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.dao.AirplaneRepository;
import com.dogukanyildirim.airlinesticketingsystem.domain.Airplane;
import com.dogukanyildirim.airlinesticketingsystem.exception.ServiceException;
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

    @Override
    public Airplane create(Airplane airplane) {
        if (Objects.isNull(airplane)) {
            throw new ServiceException(AIRPLANE_OBJECT_MUST_NOT_BE_NULL);
        }
        return airplaneRepository.save(airplane);
    }

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

    @Override
    public List<Airplane> readAll() {
        return airplaneRepository.findAll();
    }

    @Override
    public Airplane update(Airplane airplane) {
        if (Objects.isNull(airplane) || Objects.isNull(airplane.getId())) {
            throw new ServiceException(AIRPLANE_OBJECT_OR_ID_MUST_NOT_BE_NULL);
        }
        return airplaneRepository.save(airplane);
    }

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
}
