package com.yjz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String description;
    private String create_by;
    private Date create_at;
}
