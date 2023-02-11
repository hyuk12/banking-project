package com.example.bankingproject.dto.user.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter @Setter
public class UserCreateRequest {

    @NotBlank(message = "아이디는 필수 입력입니다. ")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 입력입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4글자이상 16글자 이하로 입력부탁합니다.")
    private String password;

    @Builder
    public UserCreateRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
