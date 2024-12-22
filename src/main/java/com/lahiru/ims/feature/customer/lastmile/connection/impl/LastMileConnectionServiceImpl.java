package com.lahiru.ims.feature.customer.lastmile.connection.impl;

import com.lahiru.ims.common.GenericDao;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.lastmile.connection.LastMileConnection;
import com.lahiru.ims.feature.customer.lastmile.connection.LastMileConnectionRepo;
import com.lahiru.ims.feature.customer.lastmile.connection.LastMileConnectionService;
import com.lahiru.ims.feature.customer.lastmile.connection.dto.LastMileConnectionRequestDto;
import com.lahiru.ims.feature.customer.lastmile.connection.dto.LastMileConnectionResponseDto;
import com.lahiru.ims.feature.customer.lastmile.media.LastMileMedia;
import com.lahiru.ims.feature.customer.lastmile.media.LastMileMediaService;
import com.lahiru.ims.feature.customer.lastmile.media.dto.LastMileMediaDto;
import com.lahiru.ims.feature.customer.lastmile.provider.LastMileProvider;
import com.lahiru.ims.feature.customer.lastmile.provider.LastMileProviderService;
import com.lahiru.ims.feature.customer.lastmile.provider.dto.LastMileProviderDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LastMileConnectionServiceImpl implements LastMileConnectionService {
    private final LastMileConnectionRepo lastMileConnectionRepo;
    private final LastMileMediaService mediaService;
    private final LastMileProviderService providerService;
    private final GenericDao genericDao;
    private final ModelMapper modelMapper;

    @Override
    public LastMileConnection createOne(LastMileConnectionRequestDto requestDto) throws Exception {
        LastMileConnection lastMileConnection = convertToModel(requestDto);
        return lastMileConnectionRepo.save(lastMileConnection);
    }

    @Override
    public LastMileConnection updateOne(Integer id, LastMileConnectionRequestDto requestDto) throws Exception {
        if (!lastMileConnectionRepo.existsById(id)) return createOne(requestDto);

        LastMileConnection lastMileConnection = convertToModel(requestDto);
        lastMileConnection.setId(id);
        return lastMileConnectionRepo.save(lastMileConnection);
    }

    @Override
    public LastMileConnection convertToModel(LastMileConnectionRequestDto requestDto) throws Exception {
        modelMapper.typeMap(LastMileConnectionRequestDto.class, LastMileConnection.class)
                .addMappings(mapper ->  {
                    mapper.<LastMileMediaDto>map(LastMileConnectionRequestDto::getMedia, (dest, value) -> {
                            dest.setMedia(genericDao.checkAndCreate(value, mediaService));
                    });
                    mapper.<LastMileProviderDto>map(LastMileConnectionRequestDto::getLastMileProvider, (dest, value) -> {
                            dest.setLastMileProvider(genericDao.checkAndCreate(value, providerService));
                    });
                });

        return modelMapper.map(requestDto, LastMileConnection.class);
    }

    @Override
    public LastMileConnectionResponseDto convertToDto(LastMileConnection lastMileConnection) throws Exception {
        modelMapper.typeMap(LastMileConnection.class, LastMileConnectionResponseDto.class)
                .addMappings(mapper -> {
                    mapper.<LastMileMedia>map(LastMileConnection::getMedia, (dest, value) -> {
                        dest.setMedia(new LastMileMediaDto(value.getId(), value.getName()));
                    });
                    mapper.<LastMileProvider>map(LastMileConnection::getLastMileProvider, (dest, value) -> {
                        dest.setLastMileProvider(new LastMileProviderDto(value.getId(), value.getName()));
                    });
                });

        return modelMapper.map(lastMileConnection, LastMileConnectionResponseDto.class);
    }
}
