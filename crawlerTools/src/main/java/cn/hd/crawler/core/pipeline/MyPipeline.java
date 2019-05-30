package cn.hd.crawler.core.pipeline;

import cn.hd.crawler.dao.ResultMapper;
import cn.hd.crawler.entity.Results;
import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import java.util.Map;

public class MyPipeline extends ConsolePipeline {

    private ResultMapper resultMapper;

    public String id;

    private static MyPipeline myPipeline;

    @Override
    public void process(ResultItems resultItems, Task task) {
        JSONObject jo = new JSONObject();
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            System.out.println(entry.getKey() + ":\t" + entry.getValue());
            jo.put(entry.getKey(), entry.getValue());
        }
        Results result = new Results();
        result.setSaveName(id);
        result.setResultValue(jo.toString());
        result.setUrl(resultItems.get("url"));
        resultMapper.insertSelective(result);
    }

    public MyPipeline setSaveName(String s) {
        this.id = s;
        return this;
    }

    public MyPipeline setMapper(ResultMapper resultMapper) {
        this.resultMapper = resultMapper;
        return this;
    }

}
