package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.dao.TicketPriceHistoryRepository;
import com.dogukanyildirim.airlinesticketingsystem.dao.TicketPurchaseRepository;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Flight;
import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.Passenger;
import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.PurchaseDetail;
import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.TicketPurchase;
import com.dogukanyildirim.airlinesticketingsystem.dto.mapper.ObjectMapper;
import com.dogukanyildirim.airlinesticketingsystem.dto.request.TicketPurchaseRequest;
import com.dogukanyildirim.airlinesticketingsystem.exception.ServiceException;
import com.dogukanyildirim.airlinesticketingsystem.service.FlightService;
import com.dogukanyildirim.airlinesticketingsystem.service.TicketPurchaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import static com.dogukanyildirim.airlinesticketingsystem.constant.Constants.*;
import static com.dogukanyildirim.airlinesticketingsystem.constant.ExceptionMessages.*;

@Service
public class TicketPurchaseServiceImpl implements TicketPurchaseService {
    private final TicketPurchaseRepository ticketPurchaseRepository;
    private final FlightService flightService;
    private final TicketPriceHistoryRepository ticketPriceHistoryRepository;

    public TicketPurchaseServiceImpl(TicketPurchaseRepository ticketPurchaseRepository, FlightService flightService, TicketPriceHistoryRepository ticketPriceHistoryRepository) {
        this.ticketPurchaseRepository = ticketPurchaseRepository;
        this.flightService = flightService;
        this.ticketPriceHistoryRepository = ticketPriceHistoryRepository;
    }

    @Override
    public TicketPurchase create(TicketPurchaseRequest ticketPurchaseRequest) {
        if (Objects.isNull(ticketPurchaseRequest)) {
            throw new ServiceException(TICKET_PURCHASE_OBJECT_MUST_NOT_BE_NULL);
        }
        return ticketPurchaseRepository.save(ticketPurchaseRequestToEntity(ticketPurchaseRequest));
    }

    private TicketPurchase ticketPurchaseRequestToEntity(TicketPurchaseRequest ticketPurchaseRequest) {
        TicketPurchase ticketPurchase = new TicketPurchase();

        Flight flight = flightService.read(ticketPurchaseRequest.getFlightId());

        ticketPurchase.setPurchaseCode(ticketPurchaseRequest.getPurchaseCode());
        ticketPurchase.setPnrCode(generatePNRCode());
        ticketPurchase.setFlight(flight);
        ticketPurchase.setPassengers(ObjectMapper.getInstance().mapAllSet(ticketPurchaseRequest.getPassengers(), Passenger.class));
        PurchaseDetail purchaseDetail = ObjectMapper.getInstance().map(ticketPurchaseRequest.getPurchaseDetail(), PurchaseDetail.class);
        purchaseDetail.setCreditCardNumber(formatAndMaskCreditCardNumber(purchaseDetail.getCreditCardNumber()));
        purchaseDetail.setNetPrice(ticketPurchaseRequest.getCurrentPrice() * ticketPurchase.getPassengers().size());
        ticketPurchase.setPurchaseDetail(purchaseDetail);

        return ticketPurchase;
    }

    //TODO TicketSummaryResponse oluştur. Bilet sorgularını bununla dön.
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
    public Integer getCountByFlightIdAndPurchaseCode(Integer flightId, String purchaseCode) {
        return ticketPurchaseRepository.countByFlight_IdAndPurchaseCodeAndIsCancelledIsFalse(flightId, purchaseCode);
    }

    // TODO İptali burda yap.
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

    private String formatAndMaskCreditCardNumber(String creditCardNumber) {
        final String ccn = creditCardNumber.replaceAll("\\D+", "");
        final String overlay = StringUtils.repeat(CREDIT_CARD_MASK_CHAR, CREDIT_CARD_MASK_OUT - CREDIT_CARD_MASK_IN);
        return StringUtils.overlay(ccn, overlay, CREDIT_CARD_MASK_IN, CREDIT_CARD_MASK_OUT);
    }

}
