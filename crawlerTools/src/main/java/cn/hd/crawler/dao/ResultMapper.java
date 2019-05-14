package cn.hd.crawler.dao;

import cn.hd.crawler.entity.Result;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Result record);

    int insertSelective(Result record);

    Result selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Result record);

    int updateByPrimaryKey(Result record);

    List<Result> selectBySaveName(String saveName);
}