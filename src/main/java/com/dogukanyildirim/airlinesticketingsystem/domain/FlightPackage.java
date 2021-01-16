package com.dogukanyildirim.airlinesticketingsystem.domain;

import com.dogukanyildirim.airlinesticketingsystem.domain.base.BaseEntity;
import com.dogukanyildirim.airlinesticketingsystem.enums.ClassEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Uçuş paketleri için veritabanı nesnesi
 *
 * @author dogukan.yildirim
 */

@Where(clause = "is_deleted = false")
@Getter
@Setter
@Entity
@Table(name = "flight_package")
public class FlightPackage extends BaseEntity {
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;

    @Enumerated(EnumType.STRING)
    @Column(name = "flight_class")
    private ClassEnum flightClass;

    @Column(name = "base_quota")
    private Float baseQuota;

    @Column(name = "base_price")
    private Float basePrice;

    @Column(name = "baggage")
    private Float baggage;

    @Column(name = "cabin_baggage")
    private Float cabinBaggage;
}
