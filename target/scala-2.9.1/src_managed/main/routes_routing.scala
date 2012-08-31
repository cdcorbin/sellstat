// @SOURCE:C:/software/hackr/sellstat/conf/routes
// @HASH:80fbe1f9d50d7e7bdbc5f59c28e23ed5e6d78c0e
// @DATE:Fri Aug 31 02:18:00 MDT 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {


// @LINE:6
val controllers_Landing_index0 = Route("GET", PathPattern(List(StaticPart("/"))))
                    

// @LINE:7
val controllers_Landing_load1 = Route("GET", PathPattern(List(StaticPart("/load"))))
                    

// @LINE:8
val controllers_Landing_refresh2 = Route("GET", PathPattern(List(StaticPart("/refresh/"),DynamicPart("uri", """[^/]+"""))))
                    

// @LINE:11
val controllers_ApiSchedule_oldByUri3 = Route("GET", PathPattern(List(StaticPart("/api/oldschedules/"),DynamicPart("uri", """[^/]+"""))))
                    

// @LINE:12
val controllers_ApiSchedule_byUri4 = Route("GET", PathPattern(List(StaticPart("/api/schedules/"),DynamicPart("uri", """[^/]+"""))))
                    

// @LINE:13
val controllers_ApiSchedule_apply5 = Route("POST", PathPattern(List(StaticPart("/api/schedules/"),DynamicPart("uri", """[^/]+"""))))
                    

// @LINE:15
val controllers_ApiEvent_get6 = Route("GET", PathPattern(List(StaticPart("/api/events/"),DynamicPart("uri", """[^/]+"""))))
                    

// @LINE:16
val controllers_ApiEvent_add7 = Route("POST", PathPattern(List(StaticPart("/api/events"))))
                    

// @LINE:19
val controllers_Assets_at8 = Route("GET", PathPattern(List(StaticPart("/assets/"),DynamicPart("file", """.+"""))))
                    
def documentation = List(("""GET""","""/""","""controllers.Landing.index()"""),("""GET""","""/load""","""controllers.Landing.load()"""),("""GET""","""/refresh/$uri<[^/]+>""","""controllers.Landing.refresh(uri:String)"""),("""GET""","""/api/oldschedules/$uri<[^/]+>""","""controllers.ApiSchedule.oldByUri(uri:String)"""),("""GET""","""/api/schedules/$uri<[^/]+>""","""controllers.ApiSchedule.byUri(uri:String)"""),("""POST""","""/api/schedules/$uri<[^/]+>""","""controllers.ApiSchedule.apply(uri:String)"""),("""GET""","""/api/events/$uri<[^/]+>""","""controllers.ApiEvent.get(uri:String)"""),("""POST""","""/api/events""","""controllers.ApiEvent.add()"""),("""GET""","""/assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""))
             
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Landing_index0(params) => {
   call { 
        invokeHandler(_root_.controllers.Landing.index(), HandlerDef(this, "controllers.Landing", "index", Nil))
   }
}
                    

// @LINE:7
case controllers_Landing_load1(params) => {
   call { 
        invokeHandler(_root_.controllers.Landing.load(), HandlerDef(this, "controllers.Landing", "load", Nil))
   }
}
                    

// @LINE:8
case controllers_Landing_refresh2(params) => {
   call(params.fromPath[String]("uri", None)) { (uri) =>
        invokeHandler(_root_.controllers.Landing.refresh(uri), HandlerDef(this, "controllers.Landing", "refresh", Seq(classOf[String])))
   }
}
                    

// @LINE:11
case controllers_ApiSchedule_oldByUri3(params) => {
   call(params.fromPath[String]("uri", None)) { (uri) =>
        invokeHandler(_root_.controllers.ApiSchedule.oldByUri(uri), HandlerDef(this, "controllers.ApiSchedule", "oldByUri", Seq(classOf[String])))
   }
}
                    

// @LINE:12
case controllers_ApiSchedule_byUri4(params) => {
   call(params.fromPath[String]("uri", None)) { (uri) =>
        invokeHandler(_root_.controllers.ApiSchedule.byUri(uri), HandlerDef(this, "controllers.ApiSchedule", "byUri", Seq(classOf[String])))
   }
}
                    

// @LINE:13
case controllers_ApiSchedule_apply5(params) => {
   call(params.fromPath[String]("uri", None)) { (uri) =>
        invokeHandler(_root_.controllers.ApiSchedule.apply(uri), HandlerDef(this, "controllers.ApiSchedule", "apply", Seq(classOf[String])))
   }
}
                    

// @LINE:15
case controllers_ApiEvent_get6(params) => {
   call(params.fromPath[String]("uri", None)) { (uri) =>
        invokeHandler(_root_.controllers.ApiEvent.get(uri), HandlerDef(this, "controllers.ApiEvent", "get", Seq(classOf[String])))
   }
}
                    

// @LINE:16
case controllers_ApiEvent_add7(params) => {
   call { 
        invokeHandler(_root_.controllers.ApiEvent.add(), HandlerDef(this, "controllers.ApiEvent", "add", Nil))
   }
}
                    

// @LINE:19
case controllers_Assets_at8(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(_root_.controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String])))
   }
}
                    
}
    
}
                