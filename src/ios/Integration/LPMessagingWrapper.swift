import UIKit
import LPMessagingSDK
@objc public protocol LPSDKDelegate: class {
    func chatDismissed()
}

public class LPMessagingWrapper: NSObject, BellaLPSDKDelegate {
    
    var engagement:LPGetEngagementResponse?
    weak var delegate: LPSDKDelegate?

    func chatDismissed() {
        self.delegate?.chatDismissed()
    }
    @objc public convenience init(user: LPUser?, authenticationParams: LPAuthenticationParams?) {
        self.init()
        if BellaLPMessaging.shared == nil {
            BellaLPMessaging.initialize()
        }
        self.setupLPSDKParams(user: user, authParams: authenticationParams)
    }

    private func setupLPSDKParams(user: LPUser?, authParams: LPAuthenticationParams?) {
        if let user = user {
            LPMessaging.instance.setUserProfile(user, brandID: BellaLPMessaging.accountID)
        }

        if let authParams = authParams {
            LPMessaging.instance.setAuthenticationParams(authenticationParams: authParams,
                                                         brandID: BellaLPMessaging.accountID)
        }
    }
    
    deinit {
        let conversationQuery = LPMessaging.instance.getConversationBrandQuery(BellaLPMessaging.accountID)
        LPMessaging.instance.resolveConversation(conversationQuery)
        self.engagement = nil
    }

    @objc public func getEngagement(entryPoints: [String],
                                    completion: ((Bool) -> Void)?) {

        BellaLPMessaging.shared.getEngagement(for: entryPoints) { [weak self](response) in
            
            self?.engagement = response
            completion?(response != nil)
        }
    }

    @objc public func showChat(completion: ((Bool) -> Void)?) {
        BellaLPMessaging.shared.showChat(engagementResponse: self.engagement,
                                         entryPoint: .iOSDefault,
                                         completion: completion)
    }

    @objc public func logOut(completion: ((Bool) -> Void)?) {

    }

    @objc public func clearHistory(completion: ((Bool) -> Void)?) {

    }





}

