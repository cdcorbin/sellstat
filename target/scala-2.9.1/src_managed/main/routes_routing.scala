// @SOURCE:C:/software/hackr/sellstat/conf/routes
// @HASH:ecee75be5141c1db2055f45d942a7fa415b264b1
// @DATE:Thu Aug 30 17:52:05 MDT 2012

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
                    

// @LINE:10
val controllers_ApiSchedule_byUri2 = Route("GET", PathPattern(List(StaticPart("/api/schedules/"),DynamicPart("uri", """[^/]+"""))))
                    

// @LINE:11
val controllers_ApiSchedule_apply3 = Route("POST", PathPattern(List(StaticPart("/api/schedules/"),DynamicPart("uri", """[^/]+"""))))
                    

// @LINE:14
val controllers_Assets_at4 = Route("GET", PathPattern(List(StaticPart("/assets/"),DynamicPart("file", """.+"""))))
                    
def documentation = List(("""GET""","""/""","""controllers.Landing.index()"""),("""GET""","""/load""","""controllers.Landing.load()"""),("""GET""","""/api/schedules/$uri<[^/]+>""","""controllers.ApiSchedule.byUri(uri:String)"""),("""POST""","""/api/schedules/$uri<[^/]+>""","""controllers.ApiSchedule.apply(uri:String)"""),("""GET""","""/assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""))
             
    
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
                    

// @LINE:10
case controllers_ApiSchedule_byUri2(params) => {
   call(params.fromPath[String]("uri", None)) { (uri) =>
        invokeHandler(_root_.controllers.ApiSchedule.byUri(uri), HandlerDef(this, "controllers.ApiSchedule", "byUri", Seq(classOf[String])))
   }
}
                    

// @LINE:11
case controllers_ApiSchedule_apply3(params) => {
   call(params.fromPath[String]("uri", None)) { (uri) =>
        invokeHandler(_root_.controllers.ApiSchedule.apply(uri), HandlerDef(this, "controllers.ApiSchedule", "apply", Seq(classOf[String])))
   }
}
                    

// @LINE:14
case controllers_Assets_at4(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(_root_.controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String])))
   }
}
                    
}
    
}
                