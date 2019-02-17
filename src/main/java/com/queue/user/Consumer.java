package com.queue.user;

import com.example.Message;
import com.example.QueueService;

/**
 * Created by tina on 2019/2/16.
 */
public class Consumer {
    private QueueService queueService;
    private String queueUrl;

    public Consumer(QueueService queueService, String queueUrl) {
        this.queueService = queueService;
        this.queueUrl = queueUrl;
    }

    public QueueService getQueueService() {
        return this.queueService;
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public Message pull(Integer... visibleTime) {
        QueueService queueService = getQueueService();
        String queueUrl = getQueueUrl();
        if (visibleTime != null) {
            return queueService.pull(queueUrl, visibleTime[0]);
        }
        return queueService.pull(queueUrl);
    }

    public boolean delete(String receiptHandle) {
        QueueService queueService = getQueueService();
        String queueUrl = getQueueUrl();
        return queueService.delete(queueUrl, receiptHandle);
    }
}
