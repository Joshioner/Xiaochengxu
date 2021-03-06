package com.demo.huyaxiaochengxu.mapper;

import com.demo.huyaxiaochengxu.entity.EffectEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EffectEventMapper {
    public int batchInsertEvent(@Param("effectEventList")List<EffectEvent> effectEventList);
    public List<EffectEvent> getEventsByUid(@Param("uid")String uid);
    public List<EffectEvent> getEventsByGroupId(@Param("groupId")String groupId);
    public int batchUpdateEvent(@Param("uid")String uid);

    public int updateEventById(@Param("id")int id);
    //获取在挑战中的任务
    public List<EffectEvent> getStartEventsByGroupId(@Param("groupId")String groupId);
    public List<EffectEvent> getCloseEvents(@Param("closeTime")long closeTime);
    public int batchCloseEvent(@Param("effectEventList")List<EffectEvent> effectEventList);
    //获取在挑战中的所有任务
    public List<EffectEvent> getStartEvents();
    //获取上次的挑战内容
    public List<EffectEvent> getLastEventsByUid(String uid);

}
