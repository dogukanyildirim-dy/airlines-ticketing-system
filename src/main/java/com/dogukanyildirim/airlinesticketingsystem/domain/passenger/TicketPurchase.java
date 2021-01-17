package com.dogukanyildirim.airlinesticketingsystem.domain.passenger;

import com.dogukanyildirim.airlinesticketingsystem.domain.base.BaseEntity;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Flight;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Bilet satın alım bilgileri veritabanı nesnesi
 *
 * @author dogukan.yildirim
 */

@Where(clause = "is_deleted = false")
@Getter
@Setter
@Entity
@Table(name = "ticket_purchase")
public class TicketPurchase extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private Passenger passenger;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;

    @Column(name = "purchase_code", nullable = false)
    private String purchaseCode;

    @Column(name = "is_cancelled")
    private Boolean isCancelled = false;
}
