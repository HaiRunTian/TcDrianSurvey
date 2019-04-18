package com.example.administrator.tcdrainsurvey.database;

import com.example.administrator.tcdrainsurvey.base.MyApplication;
import com.example.administrator.tcdrainsurvey.bean.DrainData;
import com.example.administrator.tcdrainsurvey.greendao.gen.DaoSession;
import com.example.administrator.tcdrainsurvey.greendao.gen.DrainDataDao;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HaiRun
 * @time 2019/4/18.10:43
 */

public class SQLiteUtils {
    private static SQLiteUtils instance;
    DrainDataDao m_drainDataDao;
    DaoSession daoSession;

    private SQLiteUtils() {
        daoSession = MyApplication.getInstances().getDaoSession();
        m_drainDataDao = daoSession.getDrainDataDao();
    }

    public static SQLiteUtils getInstance() {
        if (instance == null) {
            synchronized (SQLiteUtils.class) {
                if (instance == null) {
                    instance = new SQLiteUtils();
                }
            }
        }
        return instance;
    }

    /**
     *  增加
     */
    public void addContacts(DrainData testBean) {
        m_drainDataDao.insert(testBean);
    }

    /**
     *  删除
     */
    public void deleteContacts(DrainData testBean) {
        m_drainDataDao.delete(testBean);
    }

    /**
     *   修改
     */

    public void updateContacts(DrainData testBean) {
        m_drainDataDao.update(testBean);
    }




    /**
     *   删除表中内容
     */

    public void deleteAllContact() {
        m_drainDataDao.deleteAll();
    }

    /**
     * 查询全部数据
     * @return
     */
    public List<DrainData> queryAllContact(){
        return  m_drainDataDao.loadAll();
    }

    /**
     * 查询最后一条数据
     * @return
     */
    public DrainData queryLastContact(){
       List<DrainData> _drainDataList =  m_drainDataDao.queryBuilder().orderDesc(DrainDataDao.Properties.Id).list();
       if (_drainDataList.size() > 0){
           return _drainDataList.get(0);
       }
        return null;
    }

     /*   //条件查询
    public List selectAllContacts() {
        m_drainDataDao.detachAll();//清除缓存
        List list1 = m_drainDataDao.queryBuilder().where(m_drainDataDao.Properties.LogingId.eq(ConfigUtils.getUid())).build().list();
        return list1 == null ? new ArrayList() : list1;
    }*/
}
