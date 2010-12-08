import org.yws.wp2tumblr.helper.*
def wp = new WordpressHelper(params.blogurl,params.bloguser,params.blogpass)
request.setAttribute 'results',wp.getallpost()
forward 'wordpressresult.gtpl'