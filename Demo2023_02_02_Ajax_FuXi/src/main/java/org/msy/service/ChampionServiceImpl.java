package org.msy.service;

import org.msy.bean.Champion;
import org.msy.bean.Hero;
import org.msy.dao.ChampionDao;

import java.util.ArrayList;

/**
 * @author Msy
 * @date 2023/2/2  14:32
 */
public class ChampionServiceImpl implements ChampionService {
    ChampionDao cH = new ChampionDao();
    @Override
    public ArrayList<Champion> queryAll() {
        return cH.queryAll();
    }

    @Override
    public ArrayList<Hero> getHero() {
        return cH.getHero();
    }

    @Override
    public Champion queryById(String id) {
        return cH.queryById(id);
    }

    @Override
    public int update(Champion champion) {
        return cH.update(champion);
    }

    @Override
    public int delete(String id) {
        return cH.delete(id);
    }
}
