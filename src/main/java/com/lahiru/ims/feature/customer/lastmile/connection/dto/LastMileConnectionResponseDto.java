package com.lahiru.ims.feature.customer.lastmile.connection.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class LastMileConnectionResponseDto extends LastMileConnectionDto {
    private Integer id;
}
