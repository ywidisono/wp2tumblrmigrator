package org.yws.wp2tumblr.migrator
import java.util.Date;
import org.yws.wp2tumblr.helper.*;
import com.google.appengine.repackaged.org.apache.commons.logging.LogFactory;
/**
 * @author yohanes
 * WP To Tumblr Migrator
 * ver 1 migrate all post to tumblr and comment to disquss
 */
class WP2TumblrMigrator {
	def log = LogFactory.getLog(WP2TumblrMigrator.class)
	def WP_URL
	def WP_USER
	def WP_PASS
	def DQ_USER_KEY
	def DQ_SHORT_NAME
	def TB_EMAIL
	def TB_PASSWORD
	def TB_BLOG
	def USING_DISQUSS
	def GENERATOR = 'YWS_WP2TUMBLR'
	
	WP2TumblrMigrator(wpurl,wpuser,wppass,dqkey,dqsname,tbemail,tbpass,tblog,usingdisquss){
		WP_URL = wpurl
		WP_USER = wpuser
		WP_PASS = wppass
		DQ_USER_KEY = dqkey
		DQ_SHORT_NAME = dqsname
		TB_EMAIL = tbemail
		TB_PASSWORD = tbpass
		TB_BLOG = tblog
		USING_DISQUSS = usingdisquss
	}
	def migrate(){
		def wordpress = new WordpressHelper(WP_URL, WP_USER, WP_PASS)
		def tumblr = new TumblrHelper()
		def posts= [:]
		def rawposts = wordpress.getallpost();
		rawposts.each {
			 def tumbpost = ['email':TB_EMAIL, 'password':TB_PASSWORD, 'type':'regular', 'group':TB_BLOG, 'generator':GENERATOR,'send-to-twitter':'no']
			 tumbpost['date'] = it['date_created_gmt'].format("yyyy-MM-dd HH:mm:ss")
			 tumbpost['tags'] = it['categories'].join(',')
			 if (it['post_status'] == 'publish')
			 	tumbpost['state'] = 'published'
			 else if (it['post_status'] == 'private')
			 	tumbpost['state'] = 'draft'
			 else
			 tumbpost['state'] = it['post_status']
			 tumbpost['slug'] = it['wp_slug']
			 tumbpost['title'] = it['title']
			 tumbpost['body'] = it['description'] + it['mt_text_more']
			 tumbpost['tumbid'] = tumblr.writepost(tumbpost).getText()
			 tumbpost['url'] = "http://"+TB_BLOG+"/post/"+tumbpost['tumbid']
			 log.info 'saving' + it['postid']
			 posts.put('post'+it['postid'],tumbpost)
			 log.info 'testing getting '+posts.get('post'+it['postid'])
			 sleep(10)
		}
		if(USING_DISQUSS){
			commenttodisquss(wordpress, posts)
		}
	}
	
	private commenttodisquss(WordpressHelper wordpress, Map posts) {
		def disquss = new DisqussHelper(DQ_USER_KEY,DQ_SHORT_NAME)
		disquss.init()
		def comments =  wordpress.getallcomment()
		def threads =[:]
		comments.each {
			log.info "postid "+it['post_id']
			def post = posts.get('post'+it['post_id'])
			if(post != null){
				log.info "getting post "+post
				def t
				if(threads[it['post_id']] == null){
					log.info 'thread disquss not found'
					t = disquss.thread_by_identifier(post['tumbid'], post['title'])
					disquss.update_thread(['thread_id':t.message.thread.id,'title':post['title'], 'slug':post['slug'], 'url':post['url'], 'allow_comments':'1'])
					threads.put(it['post_id'],t)
				}else{
					log.info 'thread disquss found'
					t = threads.get(it['post_id'])
				}
				def status = disquss.create_post( ['thread_id':t.message.thread.id,'author_name':it['author'],'author_email':it['author_email'],'author_url':it['author_url'],'message':it['content'],'ip_address':it['author_ip'], 'created_at':it['date_created_gmt'].format("yyyy-MM-dd'T'HH:mm")])
				sleep(10)
			}
		}
	}

	@Override
	public String toString() {
		return "[WP_URL=" + WP_URL + ", WP_USER=" + WP_USER + ", WP_PASS=" + WP_PASS + ", DQ_USER_KEY=" + DQ_USER_KEY + ", DQ_SHORT_NAME=" + DQ_SHORT_NAME + ", TB_EMAIL=" + TB_EMAIL + ", TB_PASSWORD=" + TB_PASSWORD + ", TB_BLOG=" + TB_BLOG + ", USING_DISQUSS=" + USING_DISQUSS + ", GENERATOR=" + GENERATOR + "]";
	}	
	
}
