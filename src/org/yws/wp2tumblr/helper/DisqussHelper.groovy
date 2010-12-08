package org.yws.wp2tumblr.helper
import groovyx.net.http.*
import com.google.appengine.repackaged.org.apache.commons.logging.LogFactory;
/**
 * Disquss API Helper
 * ver 1 just basic function (create)
 * @author yohanes
 */
class DisqussHelper {
	def log = LogFactory.getLog(DisqussHelper.class)
	def URL_API = 'http://disqus.com/api/'
	def API_VER = '1.1'
	def USER_KEY =''
	def DISQUSS_SHORT_NAME=''
	def FORUM_KEY=''
	def FORUM_ID
	def POST = 'POST'
	def GET ='GET'
	
	DisqussHelper(userkey, shortname) {
		USER_KEY = userkey;
		DISQUSS_SHORT_NAME=shortname;
	}
	def init(){
		def forums = get_forum_list()
		forums.message.each{ 
			if(it['shortname'] == DISQUSS_SHORT_NAME){ 
				FORUM_KEY= get_forum_id_key(it.id).message
				FORUM_ID = it.id
			}
		}
	}
	
	def invoke(method, args, type,usingforumkey){
		try{
			if (usingforumkey) args['forum_api_key'] = FORUM_KEY
			else args['user_api_key'] = USER_KEY
			args['api_version'] = API_VER
			log.info("trying to invoke disquss "+method+" method with args "+args)
			def http = new HttpURLClient(url:URL_API+method)
			def reqs
			if(type.equals(POST)){
				reqs = http.request(body : args,requestContentType:ContentType.URLENC,contentType:ContentType.JSON,method:POST)
			}else{
				reqs = http.request(query : args)
			}
			reqs.data
		}catch(e){
			//do nothing
		}
	}
	
	def get_forum_list(){ invoke('get_forum_list',[:],GET,false) }
	def get_forum_id_key(forum_id){ invoke('get_forum_api_key',['forum_id':forum_id],GET,false) }
	
	def get_thread_list(){ invoke('get_thread_list',['user_api_key':USER_KEY,'forum_id':FORUM_ID,'start':0,'limit':999999],GET,false) }
	def get_thread_posts(thread_id){ invoke('get_thread_posts',['thread_id':thread_id,'limit':999999],GET,false) }
	def get_forum_posts (){ invoke('get_forum_posts',[:],GET,true) }
	
	def thread_by_identifier(identifier, title){invoke('thread_by_identifier',['identifier':identifier,'title':title],POST,true)}
	def update_thread(args){invoke('update_thread',args,POST,true)}
	def create_post(args){invoke('create_post',args,POST,true)
	}
}