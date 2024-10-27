/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package event;

/**
 *
 * @author luisa M
 */
public abstract class Event<T> {
    
    private T info;
    private String publisherName;
    private EventType type;

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public String getPublisherName() {
        return publisherName;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public T getInfo() {
        return this.info;
    }
}
