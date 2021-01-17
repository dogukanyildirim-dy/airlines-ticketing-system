package com.dogukanyildirim.airlinesticketingsystem.domain.management;

import com.dogukanyildirim.airlinesticketingsystem.domain.base.BaseEntity;
import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.TicketPurchase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Uçuş bilgileri için veritabanı nesnesi
 *
 * @author dogukan.yildirim
 */

@Where(clause = "is_deleted = false")
@Getter
@Setter
@Entity
@Table(name = "flight")
public class Flight extends BaseEntity {
    @Column(name = "flight_code")
    private String flightCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "route_id", referencedColumnName = "id", nullable = false)
    private Route route;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airline_id", referencedColumnName = "id", nullable = false)
    private AirlineCompany airlineCompany;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airplane_id", referencedColumnName = "id")
    private Airplane airplane;

    @Column(name = "is_directly", nullable = false)
    private Boolean isDirectly = false;

    @Column(name = "flight_date", nullable = false)
    private LocalDate flightDate;

    @Column(name = "departure_time", nullable = false)
    private LocalTime departureTime;

    @Column(name = "duration", nullable = false)
    private LocalTime duration;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FlightPackage> flightPackages;

    @JsonIgnore
    @OneToMany(mappedBy = "flight")
    private Set<TicketPurchase> ticketPurchases;

    /**
     * Uçuş paketlerini uçuş nesnesi ile beraber kaydetmek ve güncellemek için custom bir setter.
     *
     * @param flightPackages Uçuş paketleri seti
     */
    public void setFlightPackages(Set<FlightPackage> flightPackages) {
        if (CollectionUtils.isEmpty(this.flightPackages)) {
            this.flightPackages = new HashSet<>();
        } else {
            this.flightPackages.forEach(flightPackage -> flightPackage.setFlight(null));
            this.flightPackages.clear();
        }
        if (!CollectionUtils.isEmpty(flightPackages)) {
            flightPackages.forEach(flightPackage -> flightPackage.setFlight(this));
            this.flightPackages.addAll(flightPackages);
        }
    }
}

