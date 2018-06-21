package app.utils;

import org.quartz.*;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuartzScheduler {

    public void schedule() throws Exception  {

        JobDetail job = newJob(DatabaseValuesUpdate.class)
                .withIdentity("job1", "group1")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(dailyAtHourAndMinute(0, 15))
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }
}
