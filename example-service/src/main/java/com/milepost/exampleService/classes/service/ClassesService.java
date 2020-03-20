package com.milepost.exampleService.classes.service;

import com.github.pagehelper.PageInfo;
import com.milepost.api.dto.request.PageParameter;
import com.milepost.exampleApi.entity.classes.Classes;
import com.milepost.exampleApi.entity.classes.ClassesExample;
import com.milepost.service.mybatis.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ruifu Hua on 2018-11-28.
 */
@Service
@Transactional
public class ClassesService extends BaseService<Classes, ClassesExample> {

    public PageInfo<Classes> list(PageParameter pageParameter, Classes example) {
        ClassesExample classesExample = new ClassesExample();
        if(example.getName() == null){
            example.setName("");
        }
        classesExample.or().andNameLike("%"+ example.getName() +"%");
       return this.selectByExampleForPageInfo(classesExample, pageParameter);
    }
}
