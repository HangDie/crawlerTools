package cn.hd.crawler.core.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

public class MyPipeline extends ConsolePipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println(resultItems.get("title").toString());
    }
}
