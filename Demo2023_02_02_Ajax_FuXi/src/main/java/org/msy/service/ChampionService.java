package org.msy.service;

import org.msy.bean.Champion;
import org.msy.bean.Hero;

import java.util.ArrayList;

/**
 * @author Msy
 * @date 2023/2/2  14:31
 */
public interface ChampionService {
    ArrayList<Champion> queryAll();

    ArrayList<Hero> getHero();

    Champion queryById(String id);

    int update(Champion champion);

    int delete(String id);

}
