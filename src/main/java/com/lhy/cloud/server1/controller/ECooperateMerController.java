package com.lhy.cloud.server1.controller;

import com.lhy.cloud.common.constants.Constants;
import com.lhy.cloud.common.dto.ECooperateMer;
import com.lhy.cloud.common.response.Result;
import com.lhy.cloud.common.service.ECooperateMerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wenyixicodedog
 * @create: 2020-08-23
 * @description:
 */
@RestController
@RequestMapping("/server1/eCooperateMerController")
public class ECooperateMerController {

    @Autowired
    private ECooperateMerService eCooperateMerService;

    @RequestMapping(value = "/addECooperateMerInfo")
    public Result addECooperateMerInfo(@RequestBody ECooperateMer eCooperateMer) {
        Result result = new Result();
        try {
            eCooperateMerService.addECooperateMerInfo(eCooperateMer);
            result.setResultCode(Constants.RESULT_SUCCESS);
            return result;
        } catch (Exception e) {
            result.setResultCode(Constants.RESULT_FAIL);
            result.setResultMessage("新增失败");
            return result;
        }
    }

}
