package cn.hd.crawler.dao;

import cn.hd.crawler.entity.Views;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ViewsMapper {
    int deleteByPrimaryKey(Integer viewId);

    int insert(Views record);

    int insertSelective(Views record);

    Views selectByPrimaryKey(Integer viewId);

    int updateByPrimaryKeySelective(Views record);

    int updateByPrimaryKey(Views record);

    List<Views> selectAllViews();
}