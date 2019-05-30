package cn.hd.crawler.controller;

import cn.hd.crawler.dao.ResultMapper;
import cn.hd.crawler.dao.ViewsDataMapper;
import cn.hd.crawler.dao.ViewsMapper;
import cn.hd.crawler.entity.*;
import cn.hd.crawler.service.CrawlerRecordServiceImpl;
import cn.hd.crawler.service.RuleServiceImpl;
import cn.hd.crawler.utils.ServerResponse;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private RuleServiceImpl ruleService;
    @Autowired
    CrawlerRecordServiceImpl crawlerRecordService;
    @Autowired
    ResultMapper resultMapper;
    @Autowired
    ViewsDataMapper viewsDataMapper;
    @Autowired
    ViewsMapper viewsMapper;

    @RequestMapping("/save")
    @ResponseBody
    public ServerResponse save(@RequestBody Map<String, Object> params) {
        Views views = new Views();
        try {
            views.setCreateTime(new Date());
            views.setRecordId(Integer.parseInt(params.get("recordId").toString()));
//            views.setSearchString(params.get("search").toString());
            views.setViewName(params.get("view").toString());
            String searchParams = params.get("searchParams").toString();
            Integer ruleId = Integer.parseInt(params.get("ruleId").toString());
            // 分类检索
            JSONObject jo = JSONObject.parseObject(searchParams);
            CrawlerRecord crawlerRecord = crawlerRecordService.queryByRecordId(views.getRecordId());
            // 原数据集
            List<Results> results = resultMapper.selectBySaveName(crawlerRecord.getSaveTableName().substring(0, crawlerRecord.getSaveTableName().lastIndexOf("_")));
            // 结果集
            List<Integer> resultIds = new LinkedList<>();
            // 循环检索
            for (Results obj : results) {
                JSONObject resultJson = JSONObject.parseObject(obj.getResultValue());
                boolean flag = true;
                for (String key : resultJson.keySet()) {
                    if (jo.get(key) == null || jo.get(key).toString().isEmpty())
                        continue;
                    if (resultJson.get(key) == null || resultJson.get(key).toString().isEmpty())
                        flag = false;
                    if (!resultJson.get(key).toString().contains(jo.get(key).toString()))
                        flag = false;
                }
                if (flag) {
                    resultIds.add(obj.getId());
                }
            }
            viewsMapper.insert(views);
            // 结果集
            for (Integer i : resultIds) {
                ViewsDataKey viewsDataKey = new ViewsDataKey();
                viewsDataKey.setResultId(i);
                viewsDataKey.setViewId(views.getViewId());
                viewsDataMapper.insert(viewsDataKey);
            }
            return ServerResponse.createBySuccess(resultIds);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByFailure("参数错误");
        }
    }

    @RequestMapping("/queryViews")
    @ResponseBody
    public ServerResponse queryViews() {
        return ServerResponse.createBySuccess(viewsMapper.selectAllViews());
    }

    @RequestMapping("/queryData")
    @ResponseBody
    public ServerResponse queryData(Integer viewId) {
        return ServerResponse.createBySuccess(resultMapper.selectByViewId(viewId));
    }
}
