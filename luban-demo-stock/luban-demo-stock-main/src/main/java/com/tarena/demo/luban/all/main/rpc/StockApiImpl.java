package com.tarena.demo.luban.all.main.rpc;

import com.tarena.demo.luban.all.main.mapper.StockMapper;
import com.tarena.demo.luban.all.main.service.StockService;
import com.tarena.demo.luban.protocol.stock.param.StockReduceCountParam;
import com.tarena.luban.demo.stock.api.StockApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockApiImpl implements StockApi {
    @Autowired
    private StockService stockService;
    @Override
    public void reduceCommodityCount(StockReduceCountParam stockReduceCountParam) {
        stockService.reduceCommodityCount(stockReduceCountParam);
    }
}
