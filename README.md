# BrowserDemo
This is a browser demo,
#features
1.render content of url   
2.add bookmark,remove bookmark   
#used libraies 
1. Realm  
2. Picasso  
3. butterkniffe

# Improve performance of Webview
Key point is to load the images and JS(which cost most the time when loading a website) after page loaded.  
1.beofre loading url:  
  mWebView.getSettings().setJavaScriptEnabled(false);  
  mWebView.getSettings().setBlockNetworkImage(true);  
2. after onPageFinished and onReceivedTitle  
  view.getSettings().setBlockNetworkImage(false);  
  view.getSettings().setJavaScriptEnabled(true);  
  


