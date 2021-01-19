package com.dogukanyildirim.airlinesticketingsystem.domain.passenger;

import com.dogukanyildirim.airlinesticketingsystem.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Where(clause = "is_deleted = false")
@Getter
@Setter
@Entity
@Table(name = "ticket_price_history")
public class TicketPriceHistory extends BaseEntity {
    @Column(name = "purchase_code", nullable = false)
    private String purchaseCode;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "max_quota", nullable = false)
    private Integer maxQuota;
}
