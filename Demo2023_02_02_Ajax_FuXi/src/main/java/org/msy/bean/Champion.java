package org.msy.bean;

import lombok.Data;

/**
 * @author Msy
 * @date 2023/2/2  14:28
 */
@Data
public class Champion {
    private Integer id;
    private String name;
    private Integer star;
    private String nickname;
    private Hero hero;

    public Champion() {
    }

    public Champion(Integer id, String name, Integer star, String nickname, Hero hero) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.nickname = nickname;
        this.hero = hero;
    }
}
