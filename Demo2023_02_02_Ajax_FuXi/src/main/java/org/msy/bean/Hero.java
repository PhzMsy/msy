package org.msy.bean;

import lombok.Data;

/**
 * @author Msy
 * @date 2023/2/2  14:28
 */
@Data
public class Hero {
    private Integer id;
    private String name;

    public Hero() {
    }

    public Hero(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
