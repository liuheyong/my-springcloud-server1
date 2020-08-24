package com.lhy.cloud.server1.mapper;

import com.lhy.cloud.common.dto.ECooperateMer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ECooperateMerMapper {

    /**
     * @date: 2019/5/24
     * @param: [eCooperateMer]
     * @return: com.boot.com.alibabacloud.commons.serviceSub.ECooperateMer
     * @description: 详情
     */
    ECooperateMer selectECooperateMerInfo(ECooperateMer eCooperateMer);

    /**
     * @date: 2019/5/24
     * @param: [eCooperateMer]
     * @description: 插入
     */
    void addECooperateMerInfo(ECooperateMer eCooperateMer);

    /**
     * @date: 2019/5/24
     * @param: [eCooperateMer]
     * @description: 修改
     */
    void updateECooperateMerInfo(ECooperateMer eCooperateMer);

    /**
     * @date: 2019/5/24
     * @param: [eCooperateMer]
     * @return: com.boot.com.alibabacloud.commons.serviceSub.ECooperateMer
     * @description: 列表
     */
    List<ECooperateMer> queryECooperateMerListPage();
}