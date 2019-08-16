package com.sgcc.mapper;

import com.sgcc.entity.jyRecharge;

public interface jyRechargeMapper {
    /**
     *
     * @mbg.generated 2019-08-04
     */
    int deleteByPrimaryKey(String sysId);

    /**
     *
     * @mbg.generated 2019-08-04
     */
    int insert(jyRecharge record);

    /**
     *
     * @mbg.generated 2019-08-04
     */
    int insertSelective(jyRecharge record);

    /**
     *
     * @mbg.generated 2019-08-04
     */
    jyRecharge selectByPrimaryKey(String sysId);

    /**
     *
     * @mbg.generated 2019-08-04
     */
    int updateByPrimaryKeySelective(jyRecharge record);

    /**
     *
     * @mbg.generated 2019-08-04
     */
    int updateByPrimaryKey(jyRecharge record);
}