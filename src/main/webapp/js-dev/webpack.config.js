//webpack.config.js
var webpack = require('webpack');

var commonsPlugin =
    new webpack.optimize.CommonsChunkPlugin('common.js');
var minChunkSizePlugin = new webpack.optimize.MinChunkSizePlugin({});
var uglifyJsPlugin = new webpack.optimize.UglifyJsPlugin({
    compress: {
        warnings: false
    }
});

var ExtractTextPlugin = require("extract-text-webpack-plugin");

module.exports = {
    entry: {
        //test: "./src/test.js",
        index: "./src/index.js",
        addVillage: "./src/addVillage.js"

    },
    output: {
        path: 'dist',
        filename: '[name].js'
    },
    module: {
        loaders: [
            //{ test: /\.css$/, loader: 'style!css'  }
            {test: /\.css$/, loader: ExtractTextPlugin.extract("style-loader", "css-loader")},
            {test: require.resolve('jquery'), loader: 'expose?jQuery'},
            {test: /\.(woff|woff2)(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=application/font-woff'},
            {test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=application/octet-stream'},
            {test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: 'file'},
            {test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=image/svg+xml'}
        ]
    },
    plugins: [
        commonsPlugin,
        //uglifyJsPlugin,
        minChunkSizePlugin,
        new ExtractTextPlugin("[name].css", {
            disable: false,
            allChunks: true
        })
    ],
    resolve: {
        // 现在可以写 require('file') 代替 require('file.coffee')
        extensions: ['', '.js', '.json', '.coffee'],
    }
};

