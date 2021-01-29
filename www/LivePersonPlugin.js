var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'LivePersonPlugin', 'coolMethod', [arg0]);
};
exports.ConnectToBot = function (firstName,BRAND_ID,APP_INSTALLATION_ID,APP_ID,ISSUER,EntryPoint,EntryPoint_environment,EntryPoint_country,EntryPoint_language,success, error) {
    exec(success, error, 'LivePersonPlugin', 'ConnectToBot', [firstName,BRAND_ID,APP_INSTALLATION_ID,APP_ID,ISSUER,EntryPoint,EntryPoint_environment,EntryPoint_country,EntryPoint_language]);
};
