package com.milepost.exampleSingleBoot.clientDetail.service;

import com.milepost.exampleSingleBoot.clientDetail.dao.ClientDetailMapper;
import com.milepost.exampleSingleBoot.clientDetail.entity.ClientDetail;
import com.milepost.exampleSingleBoot.clientDetail.entity.ClientDetailExample;
import com.milepost.singleBoot.mybatis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ruifu Hua on 2020/2/1.
 */
@Service
public class ClientDetailService extends BaseService<ClientDetail, ClientDetailExample> {
    @Autowired
    private ClientDetailMapper clientDetailMapper;


}
