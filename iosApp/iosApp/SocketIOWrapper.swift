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

    private var manager: SocketManager?
    private var socket: SocketIOClient?


    public func isConnected() -> Bool {
        return socket?.status == .connected
    }
    public func connect(url: String,nameSpace: String, config: [String: Any]) {
        var socketConfig = SocketIOClientConfiguration()

        // Connect Params
        if let connectParams = config["connectParams"] as? [String: Any] {
            socketConfig.insert(.connectParams(connectParams))
        }

        // Handle headers
        if let headers = config["headers"] as? [String: [String]] {
            var extraHeaders = [String: String]()
            // Add custom headers
            headers.forEach { key, values in
                if let firstValue = values.first{
                    extraHeaders[key] = firstValue
                }
            }
            socketConfig.insert(.extraHeaders(extraHeaders))
        }

        if let reconnects = config["reconnects"] as? Bool {
            socketConfig.insert(.reconnects(reconnects))
        }

        if let reconnectAttempts = config["reconnectAttempts"] as? Int {
            socketConfig.insert(.reconnectAttempts(reconnectAttempts))
        }

        if let reconnectWait = config["reconnectWait"] as? Int {
            socketConfig.insert(.reconnectWait(reconnectWait))
        }

        if let forceNew = config["forceNew"] as? Bool {
            socketConfig.insert(.forceNew(forceNew))
        }

        self.manager = SocketManager(socketURL: URL(string: url)!, config: socketConfig)
        self.socket = manager?.socket(forNamespace: "/" + nameSpace)

        if let timeout = config["timeout"] as? Int {
            self.socket?.connect(timeoutAfter: Double(timeout)){}
        }else{
            self.socket?.connect{}
        }
    }

    public func disconnect() {
        socket?.disconnect()
        socket = nil
        manager = nil
    }

    public func emit(event: String, payload: String) {
        socket?.emit(event, payload)
    }

    public func on(event: String, callback: @escaping (String?) -> Void) {
        socket?.off(event)
        socket?.on(event) { data, ack in
            let message = self.rawDataToString(data: data)
            callback(message)
        }
    }

    public func off(event: String) {
        socket?.off(event)
    }

    public func offAllEvents() {
        socket?.removeAllHandlers()
    }

    func rawDataToString(data: [Any]) -> String {
        return data.last as? String ?? ""
    }
}
