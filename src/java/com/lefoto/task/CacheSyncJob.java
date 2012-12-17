/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.task;

import com.lefoto.common.filter.LoadInfoServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Eric
 */
@Component
public class CacheSyncJob{

    @Autowired
    private LoadInfoServlet loadInfoServlet;

    @Scheduled(cron = "0 0 0/4 * * ?")
    public void cacheSyncJob() {
        System.out.println("Sync job begins!");
        int count = 0;
        while (count < 5) {
            try {
                loadInfoServlet.init();
                count = 5;
            } catch (Exception e) {
                count++;
            }
        }
    }
}
