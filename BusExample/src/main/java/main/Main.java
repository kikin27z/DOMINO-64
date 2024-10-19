/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import serverClient.Server;

/**
 *
 * @author luisa M
 */
public class Main {

    public static void main(String[] args) {
        new Server(5000).initServer();
    }
}
