package com.dogukanyildirim.airlinesticketingsystem.dto;

import com.dogukanyildirim.airlinesticketingsystem.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class PurchaseDetailDTO extends BaseDTO {
    private String creditCardNumber;
    private String nameOnCreditCard;
    private String cvv;
    private String creditCardType;
    private String expirationMonth;
    private String expirationYear;
    private String email;
    private String telNo;
    private Float netPrice;
}
