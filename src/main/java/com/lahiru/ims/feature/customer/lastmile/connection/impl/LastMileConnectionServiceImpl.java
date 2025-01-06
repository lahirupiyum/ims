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
        LastMileConnection lastMileConnection = new LastMileConnection();
        lastMileConnection.setLastMileProvider(genericDao.checkAndCreate(requestDto.getLastMileProvider(), providerService));
        lastMileConnection.setMedia(genericDao.checkAndCreate(requestDto.getMedia(), mediaService));
        lastMileConnection.setBandwidth(requestDto.getBandwidth());
        lastMileConnection.setCircuitId(requestDto.getCircuitId());
        lastMileConnection.setSwitchPort(requestDto.getSwitchPort());

        return lastMileConnection;
    }

    @Override
    public LastMileConnectionResponseDto convertToDto(LastMileConnection lastMileConnection) throws Exception {
        LastMileConnectionResponseDto responseDto = new LastMileConnectionResponseDto();
        LastMileProvider lastMileProvider = lastMileConnection.getLastMileProvider();
        LastMileMedia lastMileMedia = lastMileConnection.getMedia();
        responseDto.setLastMileProvider(new LastMileProviderDto(lastMileProvider.getId(), lastMileProvider.getName()));
        responseDto.setMedia(new LastMileMediaDto(lastMileMedia.getId(), lastMileMedia.getName()));
        responseDto.setId(lastMileConnection.getId());
        responseDto.setBandwidth(lastMileConnection.getBandwidth());
        responseDto.setSwitchPort(lastMileConnection.getSwitchPort());
        responseDto.setCircuitId(lastMileConnection.getCircuitId());

        return responseDto;
    }
}
