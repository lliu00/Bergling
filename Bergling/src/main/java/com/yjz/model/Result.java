package com.yjz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int code;
    private String msg;

    private Map<String, Object> result = new HashMap<>();

    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("操作成功");
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setCode(400);
        result.setMsg("操作失败");
        return result;
    }

    public Result add(String key, Object object) {
        this.result.put(key, object);
        return this;
    }
}
