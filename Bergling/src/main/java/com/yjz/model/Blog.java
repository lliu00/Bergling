package com.yjz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String description;
    private String create_by;
    private Date create_at;

//    @Override
//    public String toString() {
//        return "[ id = " + id + ", title = " + title + ", content = " + content +
//        ", description = " + ", create_by = " + create_by + ", create_at = " + create_at +
//        " ]";
//    }
}
