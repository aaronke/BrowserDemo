# BrowserDemo
This is a browser demo,  

#features
1. render content of url   
2. add bookmark,remove bookmark  


# How to use it
1. input address(no need to care about 'http','www', you can ignore the them, just input such as 'google.com'), then enter search in keyboard.
2. when the website is loaded, you can check the star on the address bar to Mark current website, or cancel the mark of current website.
3. All the bookmarks are shown in the bookmark page, click any bookmark will leading you to the page, long click will let you to delete the bookmark.  

#used libraies 
1. Realm  
2. Picasso  
3. butterkniffe

# Improve performance of Webview
Key point is to load the images and JS(which cost most the time when loading a website) after page loaded.  
1. before loading url:  
   mWebView.getSettings().setJavaScriptEnabled(false);  
   mWebView.getSettings().setBlockNetworkImage(true);  
2. after onPageFinished  
  view.getSettings().setBlockNetworkImage(false);  
  view.getSettings().setJavaScriptEnabled(true);  
 
#Bookmark page
![alt tag](https://github.com/aaronke/BrowserDemo/blob/master/Screenshot_2016-03-23-16-22-35.png)

#Webpage
![alt tag](https://github.com/aaronke/BrowserDemo/blob/master/Screenshot_2016-03-23-16-22-48.png)


