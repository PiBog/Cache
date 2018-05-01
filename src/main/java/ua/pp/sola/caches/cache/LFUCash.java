package ua.pp.sola.caches.cache;

import ua.pp.sola.caches.dao.CrudDao;
import ua.pp.sola.caches.entity.Entity;
import org.apache.log4j.Logger;
;

import java.util.LinkedHashMap;

public class LFUCash implements Cache<String, Entity> {

    final static Logger logger = Logger.getRootLogger();

    final String message = "Hello logging!";



    private static volatile Cache cacheInstance;
    private final CrudDao systemOfRecords;
    private int cacheSize;
    private final LinkedHashMap<String, CacheEntity> storage = new LinkedHashMap<String, CacheEntity>(cacheSize);

    private LFUCash(int size, CrudDao sor) {
        this.systemOfRecords = sor;
    }

    public static Cache getCashInstance(int size, CrudDao sor) {
        Cache localCache = cacheInstance;
        if (localCache == null) {
            synchronized (Cache.class) {
                localCache = cacheInstance;
                if (localCache == null) {
                    cacheInstance = localCache = new LFUCash(size, sor);
                }
            }
        }
        return localCache;
    }

    public Entity get(String key) {
        Entity entity;
        if (storage.containsKey(key)) {
            entity = readFromCache(key);
        } else {
            entity = readFromSOR(key);
        }
        return entity;
    }

    private Entity readFromCache(String key){
        CacheEntity cacheEntity = storage.get(key);
        cacheEntity.addFrequence();
        storage.put(key,cacheEntity);
        return cacheEntity.getEntity();
    }

    private Entity readFromSOR(String key){
        
        return (Entity) systemOfRecords.read(key);

    }


    public void put(String key, Entity entity) {

    }
}
