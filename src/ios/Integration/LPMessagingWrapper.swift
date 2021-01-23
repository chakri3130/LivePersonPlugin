//
//  LPMessagingWrapper.swift
//  MyApp
//
//  Created by Omer Berger on 1/20/21.
//

import UIKit

public class LPMessagingWrapper: NSObject {
    
    @objc public func showChat() {
        if BellaLPMessaging.shared == nil {
            BellaLPMessaging.initialize()
        }
        BellaLPMessaging.shared.showChat(engagementResponse: nil, entryPoint: .iOSDefault)
    }
}
