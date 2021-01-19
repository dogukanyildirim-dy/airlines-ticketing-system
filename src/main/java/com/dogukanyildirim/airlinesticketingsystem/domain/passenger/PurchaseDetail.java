package com.dogukanyildirim.airlinesticketingsystem.domain.passenger;

import com.dogukanyildirim.airlinesticketingsystem.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Bilet satın alım detay bilgileri veritabanı nesnesi
 *
 * @author dogukan.yildirim
 */

@Where(clause = "is_deleted = false")
@Getter
@Setter
@Entity
@Table(name = "purchase_detail")
public class PurchaseDetail extends BaseEntity {
    @Column(name = "credit_card_number", length = 16, nullable = false)
    private String creditCardNumber;

    @Column(name = "name_on_credit_card", length = 50, nullable = false)
    private String nameOnCreditCard;

    @Column(name = "cvv", length = 3, nullable = false)
    private String cvv;

    @Column(name = "credit_card_type", nullable = false)
    private String creditCardType;

    @Column(name = "expiration_month", length = 2, nullable = false)
    private String expirationMonth;

    @Column(name = "expiration_year", length = 4, nullable = false)
    private String expirationYear;

    @Column(name = "net_price", nullable = false)
    private Float netPrice;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "tel_no", nullable = false)
    private String telNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_purchase_id", referencedColumnName = "id", updatable = false, nullable = false)
    private TicketPurchase ticketPurchase;
}
