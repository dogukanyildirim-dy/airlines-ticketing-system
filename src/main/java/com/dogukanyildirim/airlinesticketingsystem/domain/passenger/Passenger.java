package com.dogukanyildirim.airlinesticketingsystem.domain.passenger;

import com.dogukanyildirim.airlinesticketingsystem.domain.base.BaseEntity;
import com.dogukanyildirim.airlinesticketingsystem.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Where(clause = "is_deleted = false")
@Getter
@Setter
@Entity
@Table(name = "passenger")
public class Passenger extends BaseEntity {
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "identificationNumber", length = 11, nullable = false)
    private String identificationNumber;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private GenderEnum gender;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "ticket_purchase_id", referencedColumnName = "id")
    private TicketPurchase ticketPurchase;
}
