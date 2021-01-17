package com.dogukanyildirim.airlinesticketingsystem.domain.management;

import com.dogukanyildirim.airlinesticketingsystem.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Havayolu şirketi veritabanı nesnesi
 *
 * @author dogukan.yildirim
 */

@Where(clause = "is_deleted = false")
@Getter
@Setter
@Entity
@Table(name = "airline_company")
public class AirlineCompany extends BaseEntity {
    @Column(name = "airline_name")
    private String airlineName;

    @Column(name = "airline_alias")
    private String airlineAlias;

    @Column(name = "airline_callsign")
    private String airlineCallsign;

    @Column(name = "iata_code", length = 2)
    private String iataCode;

    @Column(name = "icao_code", length = 3)
    private String icaoCode;

    @Column(name = "country")
    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "airlineCompany")
    private Set<Flight> flights;
}
