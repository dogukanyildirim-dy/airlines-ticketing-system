package com.dogukanyildirim.airlinesticketingsystem.domain;

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
 * Uçak bilgileri veritabanı nesnesi
 *
 * @author dogukan.yildirim
 */

@Where(clause = "is_deleted = false")
@Getter
@Setter
@Entity
@Table(name = "airplane")
public class Airplane extends BaseEntity {
    @Column(name = "airplane_name")
    private String airplaneName;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Column(name = "iata_code", length = 3)
    private String iataCode;

    @Column(name = "icao_code", length = 4)
    private String icaoCode;

    @JsonIgnore
    @OneToMany(mappedBy = "airplane")
    private Set<Flight> flights;
}
