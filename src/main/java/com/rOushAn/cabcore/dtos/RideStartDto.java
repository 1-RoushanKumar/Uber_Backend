package com.rOushAn.cabcore.dtos;

public class RideStartDto {

    private String otp;

    public RideStartDto() {
    }

    public RideStartDto(String otp) {
        this.otp = otp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}