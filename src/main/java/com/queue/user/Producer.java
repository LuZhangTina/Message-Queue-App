package com.queue.user;

import com.example.QueueService;
/**
 * Created by tina on 2019/2/16.
 */
public class Producer {
    private QueueService queueService;
    private String queueUrl;

    public Producer(QueueService queueService, String queueUrl) {
        this.queueService = queueService;
        this.queueUrl = queueUrl;
    }

    public QueueService getQueueService() {
        return this.queueService;
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public boolean push(String... messages) {
        QueueService queueService = getQueueService();
        String queueUrl = getQueueUrl();
        return queueService.push(queueUrl, messages);
    }
}
