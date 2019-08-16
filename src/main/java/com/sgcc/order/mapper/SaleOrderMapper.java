package com.sgcc.order.mapper;
import com.sgcc.order.entity.SaleOrder;
public interface SaleOrderMapper {
    /**
     *
     * @mbg.generated 2019-06-25
     */
    int deleteByPrimaryKey(String saleId);

    /**
     *
     * @mbg.generated 2019-06-25
     */
    int insert(SaleOrder record);

    /**
     *
     * @mbg.generated 2019-06-25
     */
    int insertSelective(SaleOrder record);

    /**
     *
     * @mbg.generated 2019-06-25
     */
    SaleOrder selectByPrimaryKey(String saleId);

    /**
     *
     * @mbg.generated 2019-06-25
     */
    int updateByPrimaryKeySelective(SaleOrder record);

    /**
     *
     * @mbg.generated 2019-06-25
     */
    int updateByPrimaryKey(SaleOrder record);
}