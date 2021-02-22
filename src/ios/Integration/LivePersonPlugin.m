
#import <Cordova/CDV.h>
#import <UIKit/UIKit.h>
#import "QMC_Health_ID-Swift.h"
#import <LPMessagingSDK/LPMessagingSDK.h>

@interface LivePersonPlugin : CDVPlugin {
    // Member variables go here.
}
// - (void)coolMethod:(CDVInvokedUrlCommand*)command;
- (void)instantiateLPMessagingSDK:(CDVInvokedUrlCommand*)command;

@property (strong, nonatomic) UINavigationController* lpNavigationController;
@property (strong, nonatomic)LPMessagingWrapper* lpMessagingWrapper;
typedef void (^LPSDKCompletion)(BOOL completion);

@end

@implementation LivePersonPlugin

- (instancetype)init
{
    self = [super init];
    if (self) {
         
    }
    return self;
}

- (void)instantiateLPMessagingSDK:(CDVInvokedUrlCommand*)command {

    if (self.lpMessagingWrapper == nil) {
        self.lpMessagingWrapper = [[LPMessagingWrapper alloc] initWithUser: nil
                                                      authenticationParams: nil];
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"LP_MESSAgging_SDk_has_been_Initialized"];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        
    }
}


- (void)ConnectToBot:(CDVInvokedUrlCommand*)command {
    NSArray *entryPointsFromPlugin = @[[command.arguments objectAtIndex:0],[command.arguments objectAtIndex:1],[command.arguments objectAtIndex:2],[command.arguments objectAtIndex:3]];
    [self getEngagement:entryPointsFromPlugin completionAction:^(BOOL completion) {
                CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"Engegment Success"];
                [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}


- (void)showChat:(CDVInvokedUrlCommand*)command {

    [self showChatWithCompletionAction:^(BOOL completion) {
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"Conversation Opened"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];

}



- (void)getEngagement:(NSArray*)entryPoints completionAction:(LPSDKCompletion)completion {
    [self.lpMessagingWrapper getEngagementWithEntryPoints:entryPoints
                                               completion:^(BOOL succsess) {
        completion(succsess);
    }];
}

- (void)showChatWithCompletionAction:(LPSDKCompletion)completion {
    [self.lpMessagingWrapper showChatWithCompletion:^(BOOL succsess) {
        completion(succsess);
    }];
}

- (void)logOutWithCompletionAction:(LPSDKCompletion)completion  {
    [self.lpMessagingWrapper logOutWithCompletion:^(BOOL succsess) {
        completion(succsess);
    }];
}

- (void)clearHistoryWithCompletionAction:(LPSDKCompletion)completion {
    [self.lpMessagingWrapper clearHistoryWithCompletion:^(BOOL succsess) {
        completion(succsess);
    }];
}

- (void)chatDismissed {
    NSLog(@"Chat Dismissed");
}

@end


