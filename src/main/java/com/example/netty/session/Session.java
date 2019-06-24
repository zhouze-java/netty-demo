package com.example.netty.session;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 周泽
 * @date Create in 9:49 2019/6/24
 * @Description 客户端登录session
 */
@Data
@AllArgsConstructor
public class Session{

    private String userId;

    private String userName;

}
