package com.dogukanyildirim.airlinesticketingsystem.domain.management;

import com.dogukanyildirim.airlinesticketingsystem.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Rota veritabanı nesnesi
 *
 * @author dogukan.yildirim
 */

@Where(clause = "is_deleted = false")
@Getter
@Setter
@Entity
@Table(name = "route")
public class Route extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "source_airport_id", referencedColumnName = "id")
    private Airport sourceAirport;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destination_airport_id", referencedColumnName = "id")
    private Airport destinationAirport;
}
