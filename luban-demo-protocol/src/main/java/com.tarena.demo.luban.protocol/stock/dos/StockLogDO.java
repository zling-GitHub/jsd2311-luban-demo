package com.tarena.demo.luban.protocol.stock.dos;

import java.io.Serializable;
import lombok.Data;

@Data
public class StockLogDO implements Serializable {
    private Integer id;
    private String orderSn;
    private Integer reduceCount;
    private String productCode;
}
