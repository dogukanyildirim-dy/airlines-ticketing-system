package com.dogukanyildirim.airlinesticketingsystem.domain;

import com.dogukanyildirim.airlinesticketingsystem.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private Integer flightCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
    private AirlineCompany airlineCompany;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airplane_id", referencedColumnName = "id")
    private Airplane airplane;

    @Column(name = "is_directly")
    private Boolean isDirectly;

    @Column(name = "flight_date")
    private LocalDate flightDate;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "duration")
    private LocalTime duration;

    @OneToMany(mappedBy = "flight")
    private Set<FlightPackage> flightPackages;
}

