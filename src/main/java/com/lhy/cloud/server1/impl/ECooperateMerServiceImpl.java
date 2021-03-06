package com.lhy.cloud.server1.impl;

import com.lhy.cloud.common.dto.ECooperateMer;
import com.lhy.cloud.common.response.QueryECooperateMerResponse;
import com.lhy.cloud.common.service.ECooperateMerService;
import com.lhy.cloud.common.utils.UUIDUtil;
import com.lhy.cloud.server1.mapper.ECooperateMerMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author: LiuHeYong
 * @create: 2019-05-27
 * @description: ECooperateMerServiceImpl
 **/
@Service
public class ECooperateMerServiceImpl implements ECooperateMerService {

    private static final Logger logger = LoggerFactory.getLogger(ECooperateMerServiceImpl.class);

    @Autowired
    private ECooperateMerMapper eCooperateMerMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void addECooperateMerInfo(ECooperateMer eCooperateMer) throws Exception {
        try {
            eCooperateMerMapper.addECooperateMerInfo(eCooperateMer);
            updateECooperateMerInfo(eCooperateMer);
            //int a = 10 / 0;
        } catch (Exception e) {
            logger.error("系统异常", e);
            throw new Exception("系统异常");
        }
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = {Exception.class})
    public void updateECooperateMerInfo(ECooperateMer eCooperateMer) throws Exception {
        try {
            eCooperateMer.setAgentMerSeq("A2019022200000002");
            eCooperateMerMapper.updateECooperateMerInfo(eCooperateMer);
            //int a = 10 / 0;
        } catch (Exception e) {
            logger.error("系统异常", e);
            throw new Exception("系统异常");
        }
    }

    @Override
    public ECooperateMer queryECooperateMerInfo(ECooperateMer eCooperateMer) {
        Optional<ECooperateMer> optDto = Optional.ofNullable(Optional.ofNullable(eCooperateMerMapper.selectECooperateMerInfo(eCooperateMer)).orElseGet(ECooperateMer::new));
        return optDto.get();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public QueryECooperateMerResponse queryECooperateMerListPage(ECooperateMer eCooperateMer) throws Exception {
        QueryECooperateMerResponse response = new QueryECooperateMerResponse();
        try {
            // 批量插入数据测试
            List<ECooperateMer> ecList = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                ECooperateMer mer = new ECooperateMer(UUIDUtil.getUNIDX("EC", 30), "A2019022200000001", "测试数据添加", "1556442573307" + ".jpg", "https://www.baidu.com", "1", 12);
                ecList.add(mer);
            }
            //SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            //ECooperateMerMapper mapper = sqlSession.getMapper(ECooperateMerMapper.class);
            long startTime = System.currentTimeMillis();
            ecList.stream().forEach(item -> {
                //try {
                eCooperateMerMapper.addECooperateMerInfo(item);
                //int a = 10 / 0;
                //} catch (Exception e) {
                //    sqlSession.rollback();
                //    logger.error(e.getMessage(), e);
                //}
            });
            //sqlSession.commit();
            long endTime = System.currentTimeMillis();
            System.out.println("==============批量插入" + ecList.size() + "条数据测试耗时==============" + (endTime - startTime));
            //logger.info(String.valueOf(RpcContext.getContext().getAttachment("myKey")));
            List<ECooperateMer> eList = (List<ECooperateMer>) redisTemplate.opsForValue().get("eList");
            if (eList == null) {
                synchronized (this) {
                    eList = (List<ECooperateMer>) redisTemplate.opsForValue().get("eList");
                    if (eList == null) {
                        eList = eCooperateMerMapper.queryECooperateMerListPage();
                        redisTemplate.opsForValue().set("eList", eList, 60 * 10, TimeUnit.SECONDS);
                        logger.info("从数据库中获取的数据");
                    }
                }
            } else {
                logger.info("从缓存中获取的数据");
            }
            response.seteCooperateMerList(eList.subList(0, 9));
            return response;
        } catch (Exception e) {
            logger.error("系统异常", e);
            throw new Exception("系统异常");
        }
    }
}
