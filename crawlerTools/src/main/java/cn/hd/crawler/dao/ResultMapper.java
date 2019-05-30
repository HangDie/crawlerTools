package cn.hd.crawler.dao;

import cn.hd.crawler.entity.Results;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Results record);

    int insertSelective(Results record);

    Results selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Results record);

    int updateByPrimaryKey(Results record);

    List<Results> selectBySaveName(String saveName);

    List<Results> selectByViewId(Integer viewId);
}