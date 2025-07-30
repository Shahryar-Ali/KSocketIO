//
//  SocketIOWrapper.h
//  iosApp
//
//  Created by Shahryar Ali on 29/07/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

#import <Foundation/Foundation.h>
 // Auto-generated header for your KMP framework

NS_ASSUME_NONNULL_BEGIN

NS_SWIFT_NAME(SocketIOWrapper)
@interface SocketIOWrapper : NSObject

- (BOOL)isConnected;
- (void)connect:(NSString *)url
            nameSpace:(NSString *)nameSpace
              config:(NSDictionary<NSString *, id> *)config;

- (void)disconnect;
- (void)emit:(NSString *)event payload:(NSString *)payload;
- (void)on:(NSString *)event callback:(void (^)(NSString * _Nullable))callback;
- (void)off:(NSString *)event;
- (void)offAllEvents;

@end

NS_ASSUME_NONNULL_END
