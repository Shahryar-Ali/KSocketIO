//
//  SocketIOWrapper.swift
//  iosApp
//
//  Created by Shahryar Ali on 03/07/2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import SocketIO
import kSocket

public class SocketIOWrapper: SocketNativeWrapper{
    private var manager: SocketManager!
    private var socket: SocketIOClient!
    //public init(socketURL: URL, config: [String: Any]) {
      //  var socketConfig = SocketIOClientConfiguration()
        //if let connectParams = config["connectParams"] as? [String: Any] {
          //  socketConfig.insert(.connectParams(connectParams))
       // }
        // Add other config options as needed
        
        //self.manager = SocketManager(socketURL: socketURL, config: socketConfig)
        //self.socket = manager.defaultSocket
    //}

    public func connect() {
        socket.connect()
    }
    
    public func disconnect() {
        socket.disconnect()
    }
    
    public func on(_ event: String, callback: @escaping ([Any]) -> Void) {
        socket.on(event) { data, ack in
            callback(data)
        }
    }
    
    public func emit(_ event: String, items: [Any]) {
        socket.emit(event, items)
    }
    
    // Add other methods you need to expose
}
