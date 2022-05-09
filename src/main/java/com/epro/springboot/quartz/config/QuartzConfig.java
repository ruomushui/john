package com.epro.springboot.quartz.config;//package com.epro.springboot.quartz.config;
//
//import com.epro.springboot.quartz.job.task.TestJob;
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class QuartzConfig {
//
//    @Bean
//    public JobDetail jobDetail() {
//        JobDetail jobDetail = JobBuilder.newJob(TestJob.class)
//                .withIdentity("test", "test")
//                .storeDurably()
//                .build();
//        return jobDetail;
//    }
//
//    @Bean
//    public Trigger trigger() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(jobDetail())
//                .withIdentity("test", "test")
//                .startNow()
//                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
//                .build();
//        return trigger;
//    }
//}