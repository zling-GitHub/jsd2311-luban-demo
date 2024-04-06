package com.tarena.demo.luban.protocol.cart.param;

import java.io.Serializable;
import lombok.Data;

@Data
public class CartDeleteParam implements Serializable {
    private String userId;
    private String productCode;
}
