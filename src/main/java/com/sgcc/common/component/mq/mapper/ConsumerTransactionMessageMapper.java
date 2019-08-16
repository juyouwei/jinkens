package com.sgcc.common.component.mq.mapper;
import com.sgcc.common.component.mq.ConsumerTransactionMessage;
public interface ConsumerTransactionMessageMapper {
    /**
     *
     * @mbg.generated 2019-06-27
     */
    int insert(ConsumerTransactionMessage record);

    /**
     *
     * @mbg.generated 2019-06-27
     */
    int insertSelective(ConsumerTransactionMessage record);
}