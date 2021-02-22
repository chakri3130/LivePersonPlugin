var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'LivePersonPlugin', 'coolMethod', [arg0]);
};

exports.instantiateLPMessagingSDK = function([arg0],success, error){
    exec(success,error,'LivePersonPlugin', 'instantiateLPMessagingSDK', [arg0] )
}

exports.ConnectToBot = function (entryPoint_Environment,entryPoint_language,entryPoint,entryPoint_Country,success, error) {
    exec(success, error, 'LivePersonPlugin', 'ConnectToBot', [entryPoint_Environment,entryPoint_language,entryPoint,entryPoint_Country]);
};

exports.showChat = function(deviceToken,success, error){
    exec(success,error,'LivePersonPlugin', 'showChat', [deviceToken] )
}
