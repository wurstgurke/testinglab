var HtmlReporter = require('protractor-beautiful-reporter');

exports.config = {
    framework: 'jasmine',
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['spec.js'],
    multiCapabilities: [{
        browserName: 'firefox'
    }, {
        browserName: 'chrome'
    }],

    onPrepare: function() {
        // Add a screenshot reporter and store screenshots to `/tmp/screenshots`:
        jasmine.getEnv().addReporter(new HtmlReporter({
            baseDirectory: 'reports',
            sortFunction: function sortFunction(a, b) {
                if (a.cachedBase === undefined) {
                    var aTemp = a.description.split('|').reverse();
                    a.cachedBase = aTemp.slice(0).slice(0,-1);
                    a.cachedName = aTemp.slice(0).join('');
                };
                if (b.cachedBase === undefined) {
                    var bTemp = b.description.split('|').reverse();
                    b.cachedBase = bTemp.slice(0).slice(0,-1);
                    b.cachedName = bTemp.slice(0).join('');
                };

                var firstBase = a.cachedBase;
                var secondBase = b.cachedBase;

                for (var i = 0; i < firstBase.length || i < secondBase.length; i++) {

                    if (firstBase[i] === undefined) { return -1; }
                    if (secondBase[i] === undefined) { return 1; }
                    if (firstBase[i].localeCompare(secondBase[i]) === 0) { continue; }
                    return firstBase[i].localeCompare(secondBase[i]);
                }

                var firstTimestamp = a.timestamp;
                var secondTimestamp = b.timestamp;

                if(firstTimestamp < secondTimestamp) return -1;
                else return 1;
            },
            excludeSkippedSpecs: true,
            takeScreenShotsOnlyForFailedSpecs: true,
            docTitle: 'Test Reporting',
            docName: 'index.html',
            preserveDirectory: false
        }).getJasmine2Reporter());
    }
}