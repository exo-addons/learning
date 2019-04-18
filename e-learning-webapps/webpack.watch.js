const path = require('path');
const merge = require('webpack-merge');

const webpackProductionConfig = require('./webpack.prod.js');

module.exports = merge(webpackProductionConfig, {
    output: {
        path: '/home/enigma/Documents/exoPlatformNew/webapps/exo-elearning/',
        filename: 'js/[name].bundle.js'
    }
});
