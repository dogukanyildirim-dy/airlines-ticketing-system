package com.dogukanyildirim.airlinesticketingsystem.domain.passenger;

import com.dogukanyildirim.airlinesticketingsystem.domain.base.BaseEntity;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Flight;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;

    @Column(name = "purchase_code", nullable = false)
    private String purchaseCode;

    @Column(name = "pnr_code", nullable = false)
    private String pnrCode;

    @Column(name = "is_cancelled")
    private Boolean isCancelled = false;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "ticketPurchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Passenger> passengers;

    @Setter(AccessLevel.NONE)
    @OneToOne(mappedBy = "ticketPurchase", cascade = CascadeType.ALL)
    private PurchaseDetail purchaseDetail;

    public void setPurchaseDetail(PurchaseDetail purchaseDetail) {
        if (Objects.isNull(purchaseDetail)) {
            if (Objects.nonNull(this.purchaseDetail)) {
                this.purchaseDetail.setTicketPurchase(null);
            }
        } else {
            purchaseDetail.setTicketPurchase(this);
        }
        this.purchaseDetail = purchaseDetail;
    }


    public void setPassengers(Set<Passenger> passengers) {
        if (CollectionUtils.isEmpty(this.passengers)) {
            this.passengers = new HashSet<>();
        } else {
            this.passengers.forEach(passenger -> passenger.setTicketPurchase(null));
            this.passengers.clear();
        }
        if (!CollectionUtils.isEmpty(passengers)) {
            passengers.forEach(passenger -> passenger.setTicketPurchase(this));
            this.passengers.addAll(passengers);
        }
    }
}
