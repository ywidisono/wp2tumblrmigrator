package org.yws.wp2tumblr.helper
import groovyx.net.http.*
import com.google.appengine.repackaged.org.apache.commons.logging.LogFactory;
/**
 * Tumblr API Helper
 * ver 1 just basic function (read and created)
 * @author yohanes
 */
class TumblrHelper {
	def log = LogFactory.getLog(TumblrHelper.class)
	def URL_API = 'http://www.tumblr.com/api/'
	def POST = 'POST'
	def GET ='GET'
	
	def invoke(method, args, type){
 		def http = new HttpURLClient(url:URL_API+method)
		log.info("trying to invoke tumblr "+method+" method with args "+args)
		def reqs
		if(type.equals(POST)){
			reqs = http.request(body : args,requestContentType:ContentType.URLENC,contentType:ContentType.TEXT,method:POST)
		}else{
			reqs = http.request(query : args)
		}
		reqs.data
	}
	/**
	 *  Args :
	 * start - The post offset to start from. The default is 0.
	 * num - The number of posts to return. The default is 20, and the maximum is 50.
	 * type - The type of posts to return. If unspecified or empty, all types of posts are returned. Must be one of text, quote, photo, link, chat, video, or audio.
	 * id - A specific post ID to return. Use instead of start, num, or type.
	 * filter - Alternate filter to run on the text content. Allowed values:
		 o text - Plain text only. No HTML.
		 o none - No post-processing. Output exactly what the author entered. (Note: Some authors write in Markdown, which will not be converted to HTML when this option is used.)
	 * tagged - Return posts with this tag in reverse-chronological order (newest first). Optionally specify chrono=1 to sort in chronological order (oldest first).
	 * search - Search for posts with this query.
	 * state (Authenticated read required) - Specify one of the values draft, queue, or submission to list posts in the respective state.
	 * @param args
	 * @return XML
	 * <tumblr version="1.0">
	 <tumblelog ... >
	 ...
	 <feeds>
	 <feed ... />
	 <feed ... />
	 ...
	 </feeds>
	 </tumblelog>
	 <posts>
	 <post type="regular" ... >
	 <regular-title>...</regular-title>
	 <regular-body>...</regular-body>
	 </post>
	 <post type="link" ... >
	 <link-text>...</link-text>
	 <link-url>...</link-url>
	 </post>
	 <post type="quote" ... >
	 <quote-text>...</quote-text>
	 <quote-source>...</quote-source>
	 </post>
	 <post type="photo" ... >
	 <photo-caption>...</photo-caption>
	 <photo-url max-width="500">...</photo-url>
	 <photo-url max-width="400">...</photo-url>
	 ...
	 </post>
	 <post type="conversation" ... >
	 <conversation-title>...</conversation-title>
	 <conversation-text>...</conversation-text>
	 <conversation>
	 <line name="..." label="...">...</line>
	 <line name="..." label="...">...</line>
	 ...
	 </conversation>
	 </post>
	 <post type="video" ... >
	 <video-caption>...</video-caption>
	 <video-source>...</video-source>
	 <video-player>...</video-player>
	 </post>
	 <post type="audio" ... >
	 <audio-caption>...</audio-caption>
	 <audio-player>...</audio-player>
	 </post>
	 <post type="answer" ... >
	 <question>...</question>
	 <answer>...</answer>
	 </post>
	 ...
	 </posts>
	 </tumblr>
	 */
	def readposts(args){invoke('read',args,GET)}
	/**
	 * Args :
	 # email - Your account's email address.
	 # password - Your account's password.
	 # type - The post type.
	 # (content parameters) - These vary by post type.
	 # generator (optional) - A short description of the application making the request for tracking and statistics, such as "John's Widget 1.0". Must be 64 or fewer characters.
	 # date (optional) - The post date, if different from now, in the blog's timezone. Most unambiguous formats are accepted, such as '2007-12-01 14:50:02'. Dates may not be in the future.
	 # private (optional) - 1 or 0. Whether the post is private. Private posts only appear in the Dashboard or with authenticated links, and do not appear on the blog's main page.
	 # tags (optional) - Comma-separated list of post tags. You may optionally enclose tags in double-quotes.
	 # format (optional) - html or markdown.
	 # group (optional) - Post this to a secondary blog on your account, e.g. mygroup.tumblr.com (for public groups only)
	 # slug (optional) - A custom string to appear in the post's URL: myblog.tumblr.com/post/123456/this-string-right-here. URL-friendly formatting will be applied automatically. Maximum of 55 characters.
	 # state (optional) - One of the following values:
		 * published (default)
		 * draft - Save in the tumblelog's Drafts folder for later publishing.
		 * submission - Add to the tumblelog's Messages folder for consideration.
		 * queue - Add to the tumblelog's queue for automatic publishing in a few minutes or hours. To publish at a specific time in the future instead, specify an additional publish-on parameter with the date expression in the tumblelog's local time (e.g. publish-on=2010-01-01T13:34:00). If the date format cannot be understood, a 401 error will be returned and the post will not be created.
	 To change the state of an existing post, such as to switch from draft to published, follow the editing process and pass the new value as the state parameter.
	 Note: If a post has previously been saved as a draft, queue, or submission post, it will be assigned a new post ID the first time it enters the published state.
	 # send-to-twitter (optional, ignored on edits) - One of the following values, if the tumblelog has Twitter integration enabled:
		 * no (default) - Do not send this post to Twitter.
		 * auto - Send to Twitter with an automatically generated summary of the post.
		 * (any other value) - A custom message to send to Twitter for this post.
	 * @param args
	 * @return
	 * 201 Created - Success! The newly created post's ID is returned.
	 * 403 Forbidden - Your email address or password were incorrect.
	 * 400 Bad Request - There was at least one error while trying to save your post. Errors are sent in plain text, one per line.
	 */
	def writepost(args){invoke('write',args,POST)}
}
