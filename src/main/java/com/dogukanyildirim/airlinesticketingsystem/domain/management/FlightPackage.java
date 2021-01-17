package com.dogukanyildirim.airlinesticketingsystem.domain.management;

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
    @Column(name = "flight_class", nullable = false)
    private ClassEnum flightClass;

    @Column(name = "base_quota", nullable = false)
    private Integer baseQuota;

    @Column(name = "base_price", nullable = false)
    private Float basePrice;

    @Column(name = "baggage")
    private Float baggage;

    @Column(name = "cabin_baggage")
    private Float cabinBaggage;

    @Column(name = "purchase_code")
    private String purchaseCode;
}
