log.info "getting params"+params
if (!session) {
	session = request.getSession(true);
}
  
if(params.wpurl) session.setAttribute("wpurl", params.wpurl)
if(params.wpuser) session.setAttribute("wpuser", params.wpuser)
if(params.wppass) session.setAttribute("wppass", params.wppass)
if(params.tburl) session.setAttribute("tburl", params.tburl)
if(params.tbuser) session.setAttribute("tbuser", params.tbuser)
if(params.tbpass) session.setAttribute("tbpass", params.tbpass)
if(params.usedisquss) session.setAttribute("usedisquss", params.usedisquss)
if(params.dqkey) session.setAttribute("dqkey", params.dqkey)
if(params.dqname) session.setAttribute("dqname", params.dqname)

//doing routing
if(params.a == "wp"){
	forward 'tumblrform.gtpl'
}else if(params.a == "tumblr"){
	if(params.backbutton) forward 'index.gtpl'
	else{
		if(params.usedisquss) forward 'disqussform.gtpl'
		else forward 'confirmmigrate.gtpl'
	}
}else if(params.a == "dq"){log.info "dq"
	if(params.backbutton) forward 'tumblrform.gtpl'
	else forward 'confirmmigrate.gtpl'
}