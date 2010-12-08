import org.yws.wp2tumblr.helper.*
def dq = new DisqussHelper(params.disqusskey,params.disqussshortname)
dq.init()
request.setAttribute 'results',dq.get_forum_posts().message
forward 'disqussresult.gtpl'