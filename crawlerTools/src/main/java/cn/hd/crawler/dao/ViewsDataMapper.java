package cn.hd.crawler.dao;

import cn.hd.crawler.entity.ViewsDataKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ViewsDataMapper {
    int deleteByPrimaryKey(ViewsDataKey key);

    int insert(ViewsDataKey record);

    int insertSelective(ViewsDataKey record);
}