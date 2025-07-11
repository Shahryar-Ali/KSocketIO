import SwiftUI
import UIKit
import kSocket

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        SocketNativeWrapperKt.socketNativeWrapper = SocketIOWrapper()
        return true
    }
}
