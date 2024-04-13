package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;

public class MemberForm {
    private Long id;
    private String email;
    private String password;

    //생성자 추가
    public MemberForm(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    //데이터 확인용 코드
    @Override
    public String toString() {
        return "MemberForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Member toEntity() {
        return new Member(null,email,password);
    }

}
