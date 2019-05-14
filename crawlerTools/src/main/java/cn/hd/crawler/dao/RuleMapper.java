package cn.hd.crawler.dao;

import cn.hd.crawler.entity.Rule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RuleMapper {
    int deleteByPrimaryKey(Integer ruleId);

    int insert(Rule record);

    int insertSelective(Rule record);

    Rule selectByPrimaryKey(Integer ruleId);

    int updateByPrimaryKeySelective(Rule record);

    int updateByPrimaryKey(Rule record);

    List<Rule> queryAll();

    Rule queryRuleById(@Param("ruleId") Integer ruleId);

}