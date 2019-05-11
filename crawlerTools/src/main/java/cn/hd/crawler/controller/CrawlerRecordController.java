package cn.hd.crawler.controller;

import cn.hd.crawler.service.CrawlerRecordServiceImpl;
import cn.hd.crawler.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/crawlerRecord")
public class CrawlerRecordController {

    @Autowired
    CrawlerRecordServiceImpl crawlerRecordService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public ServerResponse queryAll() {
        return ServerResponse.createBySuccess(crawlerRecordService.queryAll());
    }

}
