package com.dogukanyildirim.airlinesticketingsystem.dto;

import com.dogukanyildirim.airlinesticketingsystem.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseDetailDTO extends BaseDTO {
    private String creditCardNumber;
    private String nameOnCreditCard;
    private String cvv;
    private String creditCardType;
    private String expirationMonth;
    private String expirationYear;
    private Float basePrice;
    private Float tax;
    private Float fuelCharge;
    private Float netPrice;
}
