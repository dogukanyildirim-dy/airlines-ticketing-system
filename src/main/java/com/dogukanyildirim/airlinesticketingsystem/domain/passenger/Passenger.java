package com.dogukanyildirim.airlinesticketingsystem.domain.passenger;

import com.dogukanyildirim.airlinesticketingsystem.domain.base.BaseEntity;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Flight;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

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

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "tel_no", nullable = false)
    private String telNo;

    @Column(name = "email", nullable = false)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "passenger")
    private Set<TicketPurchase> ticketPurchases;
}
