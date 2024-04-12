package org.luban.user;

import lombok.Data;

@Data
public class LubanUser {
    private String name;

    public LubanUser() {
        System.out.println("LubanUser被创建了");
    }
}
