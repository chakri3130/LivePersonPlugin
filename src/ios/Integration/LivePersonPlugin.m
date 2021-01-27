// #import <Cordova/CDV.h>
// #import "QMC_Health_ID-Swift.h"
// #import <UIKit/UIKit.h>

// @interface LivePersonPlugin : CDVPlugin {
//   // Member variables go here.
// }
// // - (void)coolMethod:(CDVInvokedUrlCommand*)command;
// - (void)ConnectToBot:(CDVInvokedUrlCommand*)command;

// @property (strong, nonatomic) UINavigationController* lpNavigationController;

// @end

// @implementation LivePersonPlugin


// - (void)ConnectToBot:(CDVInvokedUrlCommand*)command
// {

//     LPMessagingWrapper* lpMessagingWrapper =  [[LPMessagingWrapper alloc]init];
//     [lpMessagingWrapper showChat];

// // NSDictionary *chatBotDict = [[NSDictionary alloc]initWithObjectsAndKeys:@"firstName",[command.arguments objectAtIndex:0],@"lastName",[command.arguments objectAtIndex:1],@"environment", [command.arguments objectAtIndex:2],@"brandID", [command.arguments objectAtIndex:3],@"mode", [command.arguments objectAtIndex:4],@"authToken", [command.arguments objectAtIndex:5]//
         
//  }






// @end
#import <Cordova/CDV.h>
#import "QMC_Health_ID-Swift.h"
#import <UIKit/UIKit.h>
#import <LPMessagingSDK/LPMessagingSDK.h>

@interface LivePersonPlugin : CDVPlugin {
  // Member variables go here.
}
// - (void)coolMethod:(CDVInvokedUrlCommand*)command;
- (void)ConnectToBot:(CDVInvokedUrlCommand*)command;

@property (strong, nonatomic) UINavigationController* lpNavigationController;
@property (strong, nonatomic)LPMessagingWrapper* lpMessagingWrapper;
@end

@implementation LivePersonPlugin


- (void)ConnectToBot:(CDVInvokedUrlCommand*)command {

    if (self.lpMessagingWrapper == nil) {
        //Set user
        LPUser* user = [[LPUser alloc]initWithFirstName: @""
                                               lastName: @""
                                               nickName: @""
                                                    uid: @""
                                        profileImageURL: @""
                                            phoneNumber: @""
                                             employeeID: @""];

        //Set Auth
        LPAuthenticationParams* authParams =
        [[LPAuthenticationParams alloc] initWithAuthenticationCode: @""
                                                               jwt: @""
                                                       redirectURI: @""
                                             certPinningPublicKeys: @[@"", @""]
                                                authenticationType: LPAuthenticationTypeSignup];

        self.lpMessagingWrapper = [[LPMessagingWrapper alloc] initWithUser: nil
                                                      authenticationParams: nil];
    }

    //Login SDK

    //Logougt SDK

    //Clear History
    
    //Set engagement with entry point example (callback):
    /**
        ios-welcome, prodm, us, en
     */
    NSDictionary* entryPoint = @{@"entryPoint" : @"ios-welcome",
                                 @"environment" : @"prod",
                                 @"locale" : @"us",
                                 @"language" : @"en"};


    //show chat (after success callback from engagement:
    [self.lpMessagingWrapper showChat];

 }

@end


// NSDictionary *chatBotDict = [[NSDictionary alloc]initWithObjectsAndKeys:@"firstName",[command.arguments objectAtIndex:0],@"lastName",[command.arguments objectAtIndex:1],@"environment", [command.arguments objectAtIndex:2],@"brandID", [command.arguments objectAtIndex:3],@"mode", [command.arguments objectAtIndex:4],@"authToken", [command.arguments objectAtIndex:5]//

//     dispatch_async(dispatch_get_main_queue(), ^{
//         UIStoryboard *sb = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
//         self.lpNavigationController = self.lpNavigationController ? :
//         [sb instantiateViewControllerWithIdentifier:@"LPNavigation"];
//
//        // LPMessagingSwift *vc = [sb instantiateViewControllerWithIdentifier:@"LPMessagingSwift"];
//         self.lpNavigationController.modalPresentationStyle = UIModalPresentationFullScreen;
//         [self.viewController presentViewController:self.lpNavigationController
//                                           animated:YES completion:^{
//             CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"ok"];
//             // [vc connectToRoom:room];
//             [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
//         }];
//     });

