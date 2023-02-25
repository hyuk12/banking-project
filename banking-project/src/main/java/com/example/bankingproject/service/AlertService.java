package com.example.bankingproject.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AlertService {
    public void sendTransferSuccessAlert(Long userId, BigDecimal amount) {
        String message = String.format("계좌 이체가 완료되었습니다. 입금된 금액 : %s", amount);
        sendAlert(userId, message);
    }

    public void sendTransferFailedAlert(Long userId, String message) {
        message = "계좌 이체에 실패하였습니다.";
        sendAlert(userId, message);
    }

    private void sendAlert(Long userId, String message) {
        // 실제 알람을 보내는 로직을 구현하는 곳입니다.
        // 예시 : numbleAlarmService.notify(userId, message);
        // 실제 동작 : Thread.sleep(500);
        notify(userId, message);
    }

    public void notify(Long userId, String message) {
        // 알람을 보내는 것처럼 Mocking 로직을 구현
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
