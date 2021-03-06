package com.demo.huyaxiaochengxu.service;

import com.demo.huyaxiaochengxu.entity.EffectEvent;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface EffectEventService {
    public int batchInsertEvent(List<EffectEvent> effectEventList);
    public List<EffectEvent> getEventsByUid(String uid);
    public List<EffectEvent> getEventsByGroupId(String groupId);
    public int batchUpdateEvent(String uid);
    public int updateEventById(int id);
    public List<EffectEvent> getStartEventsByGroupId(String groupId);
    public List<EffectEvent> getCloseEvents(long closeTime);
    public int batchCloseEvent(List<EffectEvent> effectEventList);
    public List<EffectEvent> getStartEvents();
    public List<EffectEvent> getLastEventsByUid(String uid);
}
