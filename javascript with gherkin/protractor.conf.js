exports.config = {
  seleniumAddress: 'http://user2:1234@localhost:4444/wd/hub',
  multiCapabilities: [
    {
      browserName: 'firefox',
      'name': 'Zalenium - Protractor - Firefox',
      'proxy': { 'proxyType': 'autodetect' }
    },
    {
      browserName: 'chrome',
      'name': 'Zalenium - Protractor - Chrome',
      'proxy': { 'proxyType': 'autodetect' }
    }
  ],
  /**
   * The timeout in milliseconds for each script run on the browser. This
   * should be longer than the maximum time your application needs to
   * stabilize between tasks.
   */
  allScriptsTimeout: 25000,
  defaultTimeoutInterval: 30000,
  baseUrl: 'https://angularjs.org',
  
  framework: 'custom',  // set to "custom" instead of cucumber.
  frameworkPath: require.resolve('protractor-cucumber-framework'),  // path relative to the current config file

  specs: [
    './tests/e2e/features/*.feature'     // Specs here are the cucumber feature files
  ],
  /**
   * If true, protractor will restart the browser between each test. Default
   * value is false.
   *
   * CAUTION: This will cause your tests to slow down drastically.
   */
  restartBrowserBetweenTests: true,
  // cucumber command line options
  cucumberOpts: {
    require: [
      './tests/e2e/specs/*.js',
    ],  // require step definition files before executing features
  },
  /**
  * A callback function called once protractor is ready and available, and
  * before the specs are executed. If multiple capabilities are being run,
  * this will run once per capability.
  */
  onPrepare: function () {
    const { Given, Then, When, Before, After } = require('cucumber');
    global.Given = Given;
    global.When = When;
    global.Then = Then;
    global.Before = Before;
    global.After = After;
  }
};