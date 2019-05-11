package cn.hd.crawler.dao;

import cn.hd.crawler.entity.CrawlerRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CrawlerRecordMapper {

    List<CrawlerRecord> queryAll();

    CrawlerRecord queryRuleById(@Param("recordId") Integer recordId);

}
