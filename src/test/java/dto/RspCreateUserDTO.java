package dto;

import lombok.Data;

@Data
public class RspCreateUserDTO {
    private int code;
    private String type;
    private String message;
}