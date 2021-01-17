package com.dogukanyildirim.airlinesticketingsystem.domain.management;

import com.dogukanyildirim.airlinesticketingsystem.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Havaalanı bilgileri veritabanı nesnesi
 *
 * @author dogukan.yildirim
 */

@Where(clause = "is_deleted = false")
@Getter
@Setter
@Entity
@Table(name = "airport")
public class Airport extends BaseEntity {
    @Column(name = "airport_name")
    private String airportName;

    @Column(name = "iata_code", length = 3)
    private String iataCode;

    @Column(name = "icao_code", length = 4)
    private String icaoCode;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "altitude")
    private Integer altitude;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "sourceAirport")
    private Set<Route> routesAsSource;

    @OneToMany(mappedBy = "destinationAirport")
    private Set<Route> routesAsDestination;
}
