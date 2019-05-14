package cn.hd.crawler.controller;

import cn.hd.crawler.dao.ResultMapper;
import cn.hd.crawler.service.CrawlerRecordServiceImpl;
import cn.hd.crawler.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/crawlerRecord")
public class CrawlerRecordController {

    @Autowired
    CrawlerRecordServiceImpl crawlerRecordService;
    @Autowired
    ResultMapper resultMapper;

    @RequestMapping("/queryAll")
    @ResponseBody
    public ServerResponse queryAll() {
        return ServerResponse.createBySuccess(crawlerRecordService.queryAll());
    }

    @RequestMapping("/queryData")
    @ResponseBody
    public ServerResponse queryData(String saveName) {
        return ServerResponse.createBySuccess(resultMapper.selectBySaveName(saveName.split("_")[0]));
    }

}
