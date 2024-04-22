package com.tarena.demo.luban.all.main.mapper;

import com.tarena.demo.luban.protocol.order.dos.OrderDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    // 新增订单的方法
    @Insert("insert into order_tbl(user_id,product_code,count,total_money,order_sn) values" +
            "(#{userId},#{productCode},#{count},#{totalMoney},#{orderSn})")
    int insertOrder(OrderDO order);

    @Select("select count(1) from order_tbl where order_sn = #{orderSn}")
    Long countByOrderNo(String orderSn);
}







