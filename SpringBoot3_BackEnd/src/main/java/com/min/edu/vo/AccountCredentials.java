package com.min.edu.vo;

// 인증을 위한 자격 증명을 포함하는 클래스

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCredentials {

    private String username;
    private String password;

}
