var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'LivePersonPlugin', 'coolMethod', [arg0]);
};
exports.ConnectToBot = function (firstName,lastName,environment,brandID,mode,authToken,  success, error) {
    exec(success, error, 'LivePersonPlugin', 'ConnectToBot', [firstName,lastName,environment,brandID,mode,authToken]);
};
