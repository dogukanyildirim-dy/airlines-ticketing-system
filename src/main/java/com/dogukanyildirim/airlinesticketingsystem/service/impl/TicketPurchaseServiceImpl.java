package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.dao.TicketPriceHistoryRepository;
import com.dogukanyildirim.airlinesticketingsystem.dao.TicketPurchaseRepository;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Flight;
import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.Passenger;
import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.PurchaseDetail;
import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.TicketPriceHistory;
import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.TicketPurchase;
import com.dogukanyildirim.airlinesticketingsystem.dto.mapper.ObjectMapper;
import com.dogukanyildirim.airlinesticketingsystem.dto.request.TicketPurchaseRequest;
import com.dogukanyildirim.airlinesticketingsystem.dto.response.PassengerResponse;
import com.dogukanyildirim.airlinesticketingsystem.dto.response.TicketSummaryResponse;
import com.dogukanyildirim.airlinesticketingsystem.exception.ServiceException;
import com.dogukanyildirim.airlinesticketingsystem.service.FlightService;
import com.dogukanyildirim.airlinesticketingsystem.service.TicketPurchaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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

    /**
     * Bilet satın alma işlemlerini yapan metotudur.
     *
     * @param ticketPurchaseRequest Bilet satın alma istek gövdesi
     * @return Bilet özeti
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public TicketSummaryResponse create(TicketPurchaseRequest ticketPurchaseRequest) {
        if (Objects.isNull(ticketPurchaseRequest)) {
            throw new ServiceException(TICKET_PURCHASE_OBJECT_MUST_NOT_BE_NULL);
        }
        TicketPurchase ticketPurchase = ticketPurchaseRepository.save(ticketPurchaseRequestToEntity(ticketPurchaseRequest));
        saveTicketPriceHistory(ticketPurchase.getPurchaseCode(), ticketPurchaseRequest.getCurrentPrice(), ticketPurchaseRequest.getCurrentQuota());
        return ticketPurchaseEntityToSummaryResponse(ticketPurchase);
    }

    /**
     * Uçuş paketine tanımlanmış bilet satın alım koduyla, biletin en son hangi fiyata ve kontenjana %10 artırımı yapıldığını tutan nesneyi kaydeden metot.
     *
     * @param purchaseCode Satın alım kodu
     * @param currentPrice Mevcut fiyat
     * @param currentQuota Mevcut kontenjan
     */
    private void saveTicketPriceHistory(String purchaseCode, Float currentPrice, Integer currentQuota) {
        if (!ticketPriceHistoryRepository.existsByPurchaseCodeAndPriceAndMaxQuota(purchaseCode, currentPrice, currentQuota)) {
            TicketPriceHistory ticketPriceHistory = new TicketPriceHistory(purchaseCode, currentPrice, currentQuota);
            ticketPriceHistoryRepository.save(ticketPriceHistory);
        }
    }

    /**
     * Bilet satın alım istek gövdesini Bilet satın alım Entity nesnesine mapleyen metottur.
     *
     * @param ticketPurchaseRequest Bilet satın alım istek gövdesi
     * @return Bilet satın alım Entity
     */
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

    /**
     * PNR kodu ile bilet sorgulayan metottur.
     *
     * @param pnrCode PNR Kodu
     * @return Bilet özeti
     */
    @Override
    public TicketSummaryResponse read(String pnrCode) {
        if (Objects.isNull(pnrCode)) {
            throw new ServiceException(PNR_CODE_NOT_NULL);
        }
        Optional<TicketPurchase> resultOpt = ticketPurchaseRepository.findByPnrCodeAndIsCancelledIsFalse(pnrCode);
        if (!resultOpt.isPresent()) {
            throw new ServiceException(TICKET_NOT_FOUND_BY_PNR_CODE);
        }
        TicketPurchase ticketPurchase = resultOpt.get();
        return ticketPurchaseEntityToSummaryResponse(ticketPurchase);

    }

    /**
     * Bilet satın alım entity nesnesini Bilet Özet nesnesine mapleyen metottur.
     *
     * @param ticketPurchase Bilet satın alım Entity
     * @return Bilet özeti
     */
    private TicketSummaryResponse ticketPurchaseEntityToSummaryResponse(TicketPurchase ticketPurchase) {
        TicketSummaryResponse ticketSummaryResponse = new TicketSummaryResponse();
        ticketSummaryResponse.setPnrCode(ticketPurchase.getPnrCode());
        ticketSummaryResponse.setAirlineCompany(ticketPurchase.getFlight().getAirlineCompany().getAirlineName());
        ticketSummaryResponse.setFlightCode(ticketPurchase.getFlight().getFlightCode());
        ticketSummaryResponse.setDepartureTime(ticketPurchase.getFlight().getDepartureTime());
        ticketSummaryResponse.setArrivalTime(ticketPurchase.getFlight().getDepartureTime().plusHours(ticketPurchase.getFlight().getDuration().getHour()).plusMinutes(ticketPurchase.getFlight().getDuration().getMinute()));
        ticketSummaryResponse.setSourceAirport((ticketPurchase.getFlight().getRoute().getSourceAirport().getAirportName() + ", " + (ticketPurchase.getFlight().getRoute().getSourceAirport().getIataCode() + ", " + (ticketPurchase.getFlight().getRoute().getSourceAirport().getCity()))));
        ticketSummaryResponse.setDestinationAirport((ticketPurchase.getFlight().getRoute().getDestinationAirport().getAirportName() + ", " + (ticketPurchase.getFlight().getRoute().getDestinationAirport().getIataCode() + ", " + (ticketPurchase.getFlight().getRoute().getDestinationAirport().getCity()))));
        List<PassengerResponse> passengers = new ArrayList<>();
        ticketPurchase.getPassengers().forEach(p ->
                passengers.add(new PassengerResponse(p.getFirstName(), p.getLastName())));
        ticketSummaryResponse.setPassengers(passengers);
        return ticketSummaryResponse;
    }

    /**
     * Uçuş ve satın alım koduyla satın alınan bilet sayısını sorgulayan metottur.
     *
     * @param flightId     Uçuş ID
     * @param purchaseCode Satın alım kodu
     * @return Uçuş sayısı
     */
    @Override
    public Integer getCountByFlightIdAndPurchaseCode(Integer flightId, String purchaseCode) {
        return ticketPurchaseRepository.countByFlight_IdAndPurchaseCodeAndIsCancelledIsFalse(flightId, purchaseCode);
    }

    /**
     * PNR kodu ile bilet iptali yapan metottur.
     *
     * @param pnrCode PNR Kodu
     * @return Bilet özeti
     */
    @Override
    public TicketSummaryResponse cancelTicket(String pnrCode) {
        if (Objects.isNull(pnrCode)) {
            throw new ServiceException(PNR_CODE_NOT_NULL);
        }
        Optional<TicketPurchase> resultOpt = ticketPurchaseRepository.findByPnrCodeAndIsCancelledIsFalse(pnrCode);
        if (!resultOpt.isPresent()) {
            throw new ServiceException(TICKET_NOT_FOUND_BY_PNR_CODE);
        }
        TicketPurchase ticketPurchase = resultOpt.get();
        ticketPurchase.setIsDeleted(true);
        ticketPurchase.setIsCancelled(true);
        ticketPurchase = ticketPurchaseRepository.save(ticketPurchase);

        return ticketPurchaseEntityToSummaryResponse(ticketPurchase);
    }

    /**
     * PNR Kode üreten metottur.
     *
     * @return PNR Kodu
     */
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

    /**
     * Kredi kartı numarasını formatlayan ve maskeleyen metottur.
     * Kredi kartı numarası içindeki sayı olmayan karakterler elenir.
     * Kredi kartı 6-12. haneler arasında '*' ile maskelenir.
     *
     * @param creditCardNumber Kredi kartı numarası
     * @return Maskelenmiş kredi kartı numarası
     */
    private String formatAndMaskCreditCardNumber(String creditCardNumber) {
        final String ccn = creditCardNumber.replaceAll("\\D+", "");
        final String overlay = StringUtils.repeat(CREDIT_CARD_MASK_CHAR, CREDIT_CARD_MASK_OUT - CREDIT_CARD_MASK_IN);
        return StringUtils.overlay(ccn, overlay, CREDIT_CARD_MASK_IN, CREDIT_CARD_MASK_OUT);
    }

}
