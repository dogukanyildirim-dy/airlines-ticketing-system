package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.dao.TicketPurchaseRepository;
import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.TicketPurchase;
import com.dogukanyildirim.airlinesticketingsystem.exception.ServiceException;
import com.dogukanyildirim.airlinesticketingsystem.service.TicketPurchaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ExceptionMessages.*;

@Service
public class TicketPurchaseServiceImpl implements TicketPurchaseService {
    private final TicketPurchaseRepository ticketPurchaseRepository;

    public TicketPurchaseServiceImpl(TicketPurchaseRepository ticketPurchaseRepository) {
        this.ticketPurchaseRepository = ticketPurchaseRepository;
    }

    @Override
    public TicketPurchase create(TicketPurchase ticketPurchase) {
        if (Objects.isNull(ticketPurchase)) {
            throw new ServiceException(TICKET_PURCHASE_OBJECT_MUST_NOT_BE_NULL);
        }
        ticketPurchase.setPnrCode(generatePNRCode());
        return ticketPurchaseRepository.save(ticketPurchase);
    }

    @Override
    public TicketPurchase read(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Optional<TicketPurchase> resultOpt = ticketPurchaseRepository.findById(id);
        if (!resultOpt.isPresent()) {
            throw new ServiceException(TICKET_PURCHASE_NOT_FOUND);
        }
        return resultOpt.get();
    }

    @Override
    public List<TicketPurchase> readAll() {
        return ticketPurchaseRepository.findAll();
    }

    @Override
    public TicketPurchase update(TicketPurchase ticketPurchase) {
        if (Objects.isNull(ticketPurchase) || Objects.isNull(ticketPurchase.getId())) {
            throw new ServiceException(TICKET_PURCHASE_OBJECT_OR_ID_MUST_NOT_BE_NULL);
        }
        return ticketPurchaseRepository.save(ticketPurchase);
    }

    @Override
    public Integer getCountByFlightIdAndPurchaseCode(Integer flightId, String purchaseCode) {
        return ticketPurchaseRepository.countByFlight_IdAndPurchaseCodeAndIsCancelledIsFalse(flightId, purchaseCode);
    }

    @Override
    public TicketPurchase delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        TicketPurchase ticketPurchase = read(id);
        ticketPurchase.setIsDeleted(true);
        ticketPurchase = ticketPurchaseRepository.save(ticketPurchase);

        return ticketPurchase;
    }

    private String generatePNRCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString().toUpperCase();
    }

}
