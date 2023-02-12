package com.example.bankingproject.dto.user.request;

import com.example.bankingproject.domain.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Data
@Builder
public class UserCreateRequest {

    @NotBlank(message = "아이디는 필수 입력입니다. ")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 입력입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{4,16}$",
             message = "비밀번호는 4 ~ 16 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")

    private String password;

    private String checkedPassword;


    @Builder
    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }


}
