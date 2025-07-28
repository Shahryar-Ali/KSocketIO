//
//  kSocket-Bridging-Header.h
//  iosApp
//
//  Created by Shahryar Ali on 28/07/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

// Framework: YourSharedLibrary
// Path: iosApp/YourSharedLibrary/YourSharedLibrary-Bridging-Header.h

#ifndef kSocket_Bridging_Header_h
#define kSocket_Bridging_Header_h
#import <Foundation/Foundation.h>

// Expose KSocketUtil to Kotlin/Native
NS_SWIFT_NAME(KSocketUtil)
@interface KSocketUtil : NSObject

+ (void)initSocket NS_SWIFT_NAME(initSocket());

@end

#endif
