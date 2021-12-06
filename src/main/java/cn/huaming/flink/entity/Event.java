package cn.huaming.flink.entity;

/**
 * @auther: ZHM
 * @date: 2021/12/6 18:24
 */
public class Event {

    private String createdAt;
    private String event;
    private String productName;

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

}
