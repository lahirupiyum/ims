package com.lahiru.ims.feature.customer.customer.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CustomerResponseDto extends CustomerDto{
    private Integer id;
}
