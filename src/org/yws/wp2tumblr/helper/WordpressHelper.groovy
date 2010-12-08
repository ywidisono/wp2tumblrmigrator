package org.yws.wp2tumblr.helper
import groovy.net.xmlrpc.*
import com.google.appengine.repackaged.org.apache.commons.logging.LogFactory;
/* Wordpress Helper
 * @author yohanes
 */
class WordpressHelper {
	def log = LogFactory.getLog(WordpressHelper.class)
	def xmlrpc 
	def WP_USER =''
	def WP_PASS =''
	def BLOGID
	WordpressHelper(url,user,pass){
		WP_USER = user
		WP_PASS = pass
		log.info("trying to invoke wordpress xmlrpc with url "+url)
		xmlrpc = new XMLRPCServerProxy('http://'+url+'/xmlrpc.php')
		BLOGID = xmlrpc.wp.getUsersBlogs(WP_USER,WP_PASS)["blogid"]
	}
	/**
	 * max retrive 100000000 post
	 * @return
	 */
	def getallpost(){xmlrpc.metaWeblog.getRecentPosts(BLOGID,WP_USER,WP_PASS,100000000)}
	/**
	 * get all comment
	 * max retrive 100000000 comment
	 * @return
	 */
	def getallcomment(){xmlrpc.wp.getComments(BLOGID,WP_USER,WP_PASS,[number:100000000])}
}