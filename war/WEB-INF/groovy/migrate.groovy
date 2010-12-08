import org.yws.wp2tumblr.migrator.WP2TumblrMigrator

if(params.migratebutton){
	request.setAttribute('msg','maybe success migrate (please manual check) before rerun')
	try{
		def usingdisquss = false
		if (session.getAttribute("usedisquss") != null && session.getAttribute("usedisquss") == 'true')
			usingdisquss = true
		log.info "using disquss "+usingdisquss
		def migrate = new WP2TumblrMigrator(session.getAttribute("wpurl"),
											session.getAttribute("wpuser"),
											session.getAttribute("wppass"),
											session.getAttribute("dqkey"),
											session.getAttribute("dqname"),
											session.getAttribute("tbuser"),
											session.getAttribute("tbpass"),
											session.getAttribute("tburl"),
											usingdisquss)
		log.info "migrate "+migrate
		migrate.migrate()
		request.setAttribute('msg','success migrate')
	}catch (e){
		log.info("error "+e)
		request.setAttribute('msg','failed migrate because'+e)
	}finally { forward "migrateresult.gtpl"	}
}else forward "/"
