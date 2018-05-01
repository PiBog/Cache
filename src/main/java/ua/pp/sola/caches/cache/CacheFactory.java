package ua.pp.sola.caches.cache;

import ua.pp.sola.caches.dao.CrudDao;

public class CacheFactory {

    public <KEY, VAL> Cache<KEY, VAL> createEntityCache(int maxSize, CrudDao<KEY, VAL> systemOfRecord) {
//        return null; // TODO implement
    return LFUCash.getCashInstance(maxSize, systemOfRecord);
    }

}
