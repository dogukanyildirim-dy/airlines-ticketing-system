package com.dogukanyildirim.airlinesticketingsystem.dto.base;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseDTO {
    private Integer id;
    private Date createdDate;
    private Date lastModifiedDate;
}
