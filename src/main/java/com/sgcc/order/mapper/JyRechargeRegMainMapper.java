package com.sgcc.order.mapper;

import com.sgcc.order.entity.JyRechargeRegMain;

public interface JyRechargeRegMainMapper {
    /**
     *
     * @mbg.generated 2019-07-05
     */
    int deleteByPrimaryKey(String sysId);

    /**
     *
     * @mbg.generated 2019-07-05
     */
    int insert(JyRechargeRegMain record);

    /**
     *
     * @mbg.generated 2019-07-05
     */
    int insertSelective(JyRechargeRegMain record);

    /**
     *
     * @mbg.generated 2019-07-05
     */
    JyRechargeRegMain selectByPrimaryKey(String sysId);

    /**
     *
     * @mbg.generated 2019-07-05
     */
    int updateByPrimaryKeySelective(JyRechargeRegMain record);

    /**
     *
     * @mbg.generated 2019-07-05
     */
    int updateByPrimaryKey(JyRechargeRegMain record);
}