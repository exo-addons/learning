const path = require('path');
const { styles } = require( '@ckeditor/ckeditor5-dev-utils' );

let config = {
  context: path.resolve(__dirname, '.'),
  // set the entry point of the application
  // can use multiple entry
  entry: {
    elearning: './src/main/webapp/vue-app/elearning/main.js',
    elearningEditor:'./src/main/webapp/vue-app/elearning-editor/main.js',
    elearningPreview: './src/main/webapp/vue-app/elearning-preview/main.js'
  },
  output: {
    filename: 'js/[name].bundle.js',
    libraryTarget: 'amd'
  },
  module: {
    rules: [
      {
        // Or /ckeditor5-[^/]+\/theme\/icons\/[^/]+\.svg$/ if you want to limit this loader
        // to CKEditor 5 icons only.
        test: /\.svg$/,
        use: [ 'raw-loader' ]
      },
      {
        // Or /ckeditor5-[^/]+\/theme\/[^/]+\.css$/ if you want to limit this loader
        // to CKEditor 5 theme only.
        test: /\.css$/,
        use: [
          {
            loader: 'style-loader',
            options: {
              singleton: true
            }
          },
          {
            loader: 'postcss-loader',
            options: styles.getPostCssConfig( {
              themeImporter: {
                themePath: require.resolve( '@ckeditor/ckeditor5-theme-lark' )
              },
              minify: true
            } )
          },
        ]
      },
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: [
          'babel-loader',
          'eslint-loader'
        ]
      },
      {
        test: /\.vue$/,
        use: [
          'vue-loader',
          'eslint-loader'
        ]
      }
    ]
  },
  externals: {
    vue: 'Vue',
    vuetify: 'Vuetify',
  }
};

module.exports = config;
