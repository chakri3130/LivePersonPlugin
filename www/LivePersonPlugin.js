var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'LivePersonPlugin', 'coolMethod', [arg0]);
};
exports.ConnectToBot = function (firstName,brandID,appInstallationID,appID,issuer,entryPoint,entryPoint_Environment,entryPoint_Country,entryPoint_language,success, error) {
    exec(success, error, 'LivePersonPlugin', 'ConnectToBot', [firstName,brandID,appInstallationID,appID,issuer,entryPoint,entryPoint_Environment,entryPoint_Country,entryPoint_language]);
};
