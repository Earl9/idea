package com.wild.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.wild.common.GenerateUUID;
import com.wild.entity.User;
import com.wild.entity.vo.UserExcelVo;
import com.wild.mapper.master.UserMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
public class UploadDataListener implements ReadListener<UserExcelVo> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 500;
    private List<UserExcelVo> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    
    private UserMapper userMapper;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param userMapper
     */
    public UploadDataListener(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(UserExcelVo data, AnalysisContext context) {
        log.info("解析到一条数据:{}", (data));
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        ArrayList<User> users = new ArrayList<>();
        cachedDataList.stream().forEach(x->{
            User user = new User();
            user.setUserId(new GenerateUUID().getUuid());
            user.setUserName(x.getUserName());
            user.setPassword(x.getPassword());
            user.setPhone(x.getPhone());
            user.setDel(x.getDel().equals("正常") ? 0 : 1);
            user.setCreateTime(x.getCreateTime());
//            user.setCards(x.getCards());
            users.add(user);
        });
        userMapper.batchInsert(users);
        log.info("存储数据库成功！");
    }
}