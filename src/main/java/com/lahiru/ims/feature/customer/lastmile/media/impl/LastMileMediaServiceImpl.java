package com.lahiru.ims.feature.customer.lastmile.media.impl;

import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.lastmile.media.LastMileMedia;
import com.lahiru.ims.feature.customer.lastmile.media.LastMileMediaRepo;
import com.lahiru.ims.feature.customer.lastmile.media.LastMileMediaService;
import com.lahiru.ims.feature.customer.lastmile.media.dto.LastMileMediaDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LastMileMediaServiceImpl implements LastMileMediaService {
    private final LastMileMediaRepo lastMileMediaRepo;
    private final ModelMapper modelMapper;

    @Override
    public LastMileMedia createOne(String name) throws Exception {
        LastMileMedia lastMileMedia = new LastMileMedia(name);
        return lastMileMediaRepo.save(lastMileMedia);
    }

    @Override
    public List<LastMileMediaDto> getAll() throws Exception {
        List<LastMileMedia> all = lastMileMediaRepo.findAll();
        return all.stream().map(media -> modelMapper.map(media, LastMileMediaDto.class)).toList();
    }

    @Override
    public LastMileMedia findOne(Integer id) throws Exception {
        return lastMileMediaRepo.findById(id).orElseThrow(() -> new NotFoundException("Last Mile Media"));
    }
}
