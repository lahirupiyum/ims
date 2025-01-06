package com.lahiru.ims.common.service;

import java.util.List;

public interface GenericSearchService <ResponseDto> {
    List<ResponseDto> searchItem (String searchKey) throws Exception;
}
