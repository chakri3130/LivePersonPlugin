// /********* LivePersonPlugin.m Cordova Plugin Implementation *******/

// #import <Cordova/CDV.h>

// @interface LivePersonPlugin : CDVPlugin {
//   // Member variables go here.
// }

// - (void)coolMethod:(CDVInvokedUrlCommand*)command;
// @end

// @implementation LivePersonPlugin

// - (void)coolMethod:(CDVInvokedUrlCommand*)command
// {
//     CDVPluginResult* pluginResult = nil;
//     NSString* echo = [command.arguments objectAtIndex:0];

//     if (echo != nil && [echo length] > 0) {
//         pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
//     } else {
//         pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
//     }

//     [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
// }

// @end
/********* LivePerson.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>
#import "QMC Health ID-Swift.h"
#import <UIKit/UIKit.h>

@interface LivePersonPlugin : CDVPlugin {
  // Member variables go here.
}
// - (void)coolMethod:(CDVInvokedUrlCommand*)command;
- (void)ConnectToBot:(CDVInvokedUrlCommand*)command;

@property (strong, nonatomic) UINavigationController* lpNavigationController;

@end

@implementation LivePersonPlugin


- (void)ConnectToBot:(CDVInvokedUrlCommand*)command
{

    LPMessagingWrapper* lpMessagingWrapper =  [[LPMessagingWrapper alloc]init];
    [lpMessagingWrapper showChat];

// NSDictionary *chatBotDict = [[NSDictionary alloc]initWithObjectsAndKeys:@"firstName",[command.arguments objectAtIndex:0],@"lastName",[command.arguments objectAtIndex:1],@"environment", [command.arguments objectAtIndex:2],@"brandID", [command.arguments objectAtIndex:3],@"mode", [command.arguments objectAtIndex:4],@"authToken", [command.arguments objectAtIndex:5]//
         
 }






@end

