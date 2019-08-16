package com.sgcc.common.component.mq.mapper;

import com.sgcc.common.component.mq.ProducerTransactionMessage;

public interface ProducerTransactionMessageMapper {
    /**
     *
     * @mbg.generated 2019-06-27
     */
    int insert(ProducerTransactionMessage record);

    /**
     *
     * @mbg.generated 2019-06-27
     */
    int insertSelective(ProducerTransactionMessage record);
}