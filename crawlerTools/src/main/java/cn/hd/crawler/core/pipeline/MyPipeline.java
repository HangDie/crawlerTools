package cn.hd.crawler.core.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

public class MyPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println(resultItems.get("title").toString());
    }
}
