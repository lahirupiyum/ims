package com.lahiru.ims.feature.customer.lastmile.provider.impl;

import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.lastmile.provider.LastMileProvider;
import com.lahiru.ims.feature.customer.lastmile.provider.LastMileProviderRepo;
import com.lahiru.ims.feature.customer.lastmile.provider.LastMileProviderService;
import com.lahiru.ims.feature.customer.lastmile.provider.dto.LastMileProviderDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LastMileProviderServiceImpl implements LastMileProviderService {
    private final LastMileProviderRepo lastMileProviderRepo;
    private final ModelMapper modelMapper;

    @Override
    public LastMileProvider createOne(String name) throws Exception {
        LastMileProvider lastMileProvider = new LastMileProvider(name);
        return lastMileProviderRepo.save(lastMileProvider);
    }

    @Override
    public List<LastMileProviderDto> getAll() throws Exception {
        List<LastMileProvider> all = lastMileProviderRepo.findAll();
        return all.stream().map(provider -> modelMapper.map(provider, LastMileProviderDto.class)).toList();
    }

    @Override
    public LastMileProvider findOne(Integer id) throws Exception {
        return lastMileProviderRepo.findById(id).orElseThrow(() -> new NotFoundException("Last Mile Provider"));
    }
}
