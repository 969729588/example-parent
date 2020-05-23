package com.milepost.exampleSingleBoot.clientDetail.dao;

import com.milepost.exampleSingleBoot.clientDetail.entity.ClientDetail;
import com.milepost.exampleSingleBoot.clientDetail.entity.ClientDetailExample;
import com.milepost.singleBoot.mybatis.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Ruifu Hua on 2020/2/1.
 */
@Mapper
public interface ClientDetailMapper extends BaseMapper<ClientDetail, ClientDetailExample> {

}
