package com.moduls.myxxljobhttp.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @description:
 * @program: my-elastic-job
 * @author: fangkun
 * @version： 1.0.0
 * @create: 2019-07-04
 */
@Component
@JobHandler(value = "myXxlJob")
public class MyXxlJob extends IJobHandler {

    private Logger logger = LoggerFactory.getLogger(MyXxlJob.class);
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        XxlJobLogger.log("beat at: ---->>>>");
        System.out.println(LocalTime.now().atDate(LocalDate.now()).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))+"-->>> 端口号为8889 MyXxlJob执行任务");

        logger.info(LocalTime.now().atDate(LocalDate.now()).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))+
                "执行我的服务了，方法com.moduls.myxxljobhttp.task.MyXxlJob正在run");
        return SUCCESS;
    }
}
