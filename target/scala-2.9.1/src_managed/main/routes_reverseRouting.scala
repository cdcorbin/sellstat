// @SOURCE:C:/software/hackr/sellstat/conf/routes
// @HASH:111f19d591495893cb0a3651d6491fdf5fec01b8
// @DATE:Thu Aug 30 19:07:51 MDT 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:17
// @LINE:14
// @LINE:13
// @LINE:11
// @LINE:10
// @LINE:7
// @LINE:6
package controllers {

// @LINE:17
class ReverseAssets {
    


 
// @LINE:17
def at(file:String) = {
   Call("GET", "/assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                        

                      
    
}
                            

// @LINE:14
// @LINE:13
class ReverseApiEvent {
    


 
// @LINE:14
def add() = {
   Call("POST", "/api/events")
}
                                                        
 
// @LINE:13
def get(uri:String) = {
   Call("GET", "/api/events/" + implicitly[PathBindable[String]].unbind("uri", uri))
}
                                                        

                      
    
}
                            

// @LINE:7
// @LINE:6
class ReverseLanding {
    


 
// @LINE:6
def index() = {
   Call("GET", "/")
}
                                                        
 
// @LINE:7
def load() = {
   Call("GET", "/load")
}
                                                        

                      
    
}
                            

// @LINE:11
// @LINE:10
class ReverseApiSchedule {
    


 
// @LINE:11
def apply(uri:String) = {
   Call("POST", "/api/schedules/" + implicitly[PathBindable[String]].unbind("uri", uri))
}
                                                        
 
// @LINE:10
def byUri(uri:String) = {
   Call("GET", "/api/schedules/" + implicitly[PathBindable[String]].unbind("uri", uri))
}
                                                        

                      
    
}
                            
}
                    


// @LINE:17
// @LINE:14
// @LINE:13
// @LINE:11
// @LINE:10
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:17
class ReverseAssets {
    


 
// @LINE:17
def at = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"/assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:14
// @LINE:13
class ReverseApiEvent {
    


 
// @LINE:14
def add = JavascriptReverseRoute(
   "controllers.ApiEvent.add",
   """
      function() {
      return _wA({method:"POST", url:"/api/events"})
      }
   """
)
                                                        
 
// @LINE:13
def get = JavascriptReverseRoute(
   "controllers.ApiEvent.get",
   """
      function(uri) {
      return _wA({method:"GET", url:"/api/events/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("uri", uri)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:7
// @LINE:6
class ReverseLanding {
    


 
// @LINE:6
def index = JavascriptReverseRoute(
   "controllers.Landing.index",
   """
      function() {
      return _wA({method:"GET", url:"/"})
      }
   """
)
                                                        
 
// @LINE:7
def load = JavascriptReverseRoute(
   "controllers.Landing.load",
   """
      function() {
      return _wA({method:"GET", url:"/load"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:11
// @LINE:10
class ReverseApiSchedule {
    


 
// @LINE:11
def apply = JavascriptReverseRoute(
   "controllers.ApiSchedule.apply",
   """
      function(uri) {
      return _wA({method:"POST", url:"/api/schedules/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("uri", uri)})
      }
   """
)
                                                        
 
// @LINE:10
def byUri = JavascriptReverseRoute(
   "controllers.ApiSchedule.byUri",
   """
      function(uri) {
      return _wA({method:"GET", url:"/api/schedules/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("uri", uri)})
      }
   """
)
                                                        

                      
    
}
                            
}
                    


// @LINE:17
// @LINE:14
// @LINE:13
// @LINE:11
// @LINE:10
// @LINE:7
// @LINE:6
package controllers.ref {

// @LINE:17
class ReverseAssets {
    


 
// @LINE:17
def at(path:String, file:String) = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]))
)
                              

                      
    
}
                            

// @LINE:14
// @LINE:13
class ReverseApiEvent {
    


 
// @LINE:14
def add() = new play.api.mvc.HandlerRef(
   controllers.ApiEvent.add(), HandlerDef(this, "controllers.ApiEvent", "add", Seq())
)
                              
 
// @LINE:13
def get(uri:String) = new play.api.mvc.HandlerRef(
   controllers.ApiEvent.get(uri), HandlerDef(this, "controllers.ApiEvent", "get", Seq(classOf[String]))
)
                              

                      
    
}
                            

// @LINE:7
// @LINE:6
class ReverseLanding {
    


 
// @LINE:6
def index() = new play.api.mvc.HandlerRef(
   controllers.Landing.index(), HandlerDef(this, "controllers.Landing", "index", Seq())
)
                              
 
// @LINE:7
def load() = new play.api.mvc.HandlerRef(
   controllers.Landing.load(), HandlerDef(this, "controllers.Landing", "load", Seq())
)
                              

                      
    
}
                            

// @LINE:11
// @LINE:10
class ReverseApiSchedule {
    


 
// @LINE:11
def apply(uri:String) = new play.api.mvc.HandlerRef(
   controllers.ApiSchedule.apply(uri), HandlerDef(this, "controllers.ApiSchedule", "apply", Seq(classOf[String]))
)
                              
 
// @LINE:10
def byUri(uri:String) = new play.api.mvc.HandlerRef(
   controllers.ApiSchedule.byUri(uri), HandlerDef(this, "controllers.ApiSchedule", "byUri", Seq(classOf[String]))
)
                              

                      
    
}
                            
}
                    
                