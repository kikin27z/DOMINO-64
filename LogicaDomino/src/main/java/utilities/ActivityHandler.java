/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 *
 * @author luisa M
 */
public abstract class ActivityHandler {
    protected ActivityHandler nextHandler;

    public void setNextHandler(ActivityHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(int level);
}
