package com.joyin.simplemail.pojo;

import lombok.Data;

/**
 * <br/>
 *
 * @author yangchaozheng
 * @date 2019/11/11 10:19
 */
@Data
public class EmailEntity {
    private String receiver;
    private String subject;
    private String text;
    private String content;
}
