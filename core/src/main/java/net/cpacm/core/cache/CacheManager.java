package net.cpacm.core.cache;

import android.content.Context;
import android.util.LruCache;

import net.cpacm.core.GlobalApplication;

import java.io.Serializable;
import java.util.Objects;

/**
 * 缓存管理类，二级缓存。其中一级缓存使用ACache<br/>
 * Cache Manager.L2 chcae.File cache use ACache.
 *
 * @Auther: cpacm
 * @Date: 2015/10/23 0023-上午 11:23
 */
public class CacheManager {

    private Context mContext;
    private LruCache<String, String> jsonCache;
    private LruCache<String, Object> objCache;
    private final static String CACHE_DIR = "ACG_CACHE";
    private static CacheManager instance = null;

    //singleton
    private CacheManager() {
        this.mContext = GlobalApplication.getInstance().getApplicationContext();
        int memClass = GlobalApplication.getInstance().getMemSize();
        int cache_max_size = Math.max(1024 * 1024 * 4, 1024 * 1024 * memClass / 8 / 2);
        jsonCache = new LruCache<>(cache_max_size);
        objCache = new LruCache<>(cache_max_size);
    }

    /**
     * get CacheManager singleton
     *
     * @return
     */
    public static CacheManager getInstance() {
        if (instance == null) {
            synchronized (CacheManager.class) {
                if (instance == null) {
                    instance = new CacheManager();
                }
            }
        }
        return instance;
    }

    /**
     * 获得缓存<br/>
     * get ACache
     *
     * @return
     */
    private ACache getACache() {
        return ACache.get(mContext, CACHE_DIR);
    }

    /**
     * 存放字符串缓存<br/>
     * store String cache into memory and file
     *
     * @param key
     * @param json
     */
    public void put(String key, String json) {
        getACache().put(key, json);
        jsonCache.put(key, json);
    }

    /**
     * 存放序列化对象缓存<br/>
     * store obj cache into memory and file
     *
     * @param key
     * @param obj
     */
    public void put(String key, Serializable obj) {
        getACache().put(key, obj);
        objCache.put(key, obj);
    }

    /**
     * just put to cache.
     *
     * @param key
     * @param json
     */
    public void putCache(String key, String json) {
        jsonCache.put(key, json);
    }

    /**
     * 先从内存中获得缓存，内存中没有时从文件中获取<br/>
     * First get cache from memory,if not exist get from file cache.
     *
     * @param key
     * @return if return null explain there has no cache
     */
    public String get(String key) {
        String json = null;
        if (jsonCache.get(key) == null) {
            json = getACache().getAsString(key);
            if (json != null)
                jsonCache.put(key, json);
        }
        return jsonCache.get(key);
    }

    /**
     * 获取序列化对象<br/>
     * get serializable obj
     *
     * @param key
     * @return
     */
    public Object getObj(String key) {
        Object obj = null;
        if (objCache.get(key) == null) {
            obj = getACache().getAsObject(key);
            if (obj != null)
                objCache.put(key, obj);
        }
        return objCache.get(key);
    }

    public void clear() {
        jsonCache.evictAll();
        objCache.evictAll();
        getACache().clear();
    }

}
