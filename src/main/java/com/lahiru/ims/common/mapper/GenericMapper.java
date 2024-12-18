package com.lahiru.ims.common.mapper;

import java.util.List;

public interface GenericMapper<ReqD, ResD, E> {
    // Map Entity to Response DTO
    ResD toResponseDto(E entity);

    // Map Response DTO to Entity
    E toEntityFromResponseDto(ResD responseDto);

    // Map Request DTO to Entity
    E toEntity(ReqD requestDto);

    // Map Entity to Request DTO
    ReqD toRequestDto(E entity);

    // Map List of Entities to List of Response DTOs
    List<ResD> toResponseDtoList(List<E> entities);

    // Map List of Response DTOs to List of Entities
    List<E> toEntityListFromResponseDtos(List<ResD> responseDtos);

    // Map List of Request DTOs to List of Entities
    List<E> toEntityListFromRequestDtos(List<ReqD> requestDtos);

    // Map List of Entities to List of Request DTOs
    List<ReqD> toRequestDtoList(List<E> entities);

}
