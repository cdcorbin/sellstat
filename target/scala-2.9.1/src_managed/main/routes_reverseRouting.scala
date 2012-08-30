// @SOURCE:C:/software/hackr/sellstat/conf/routes
// @HASH:c7f5df06a2cdfa1347eeaa68d8e5b24c9bc3c726
// @DATE:Thu Aug 30 17:21:25 MDT 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:13
class ReverseAssets {
    


 
// @LINE:13
def at(file:String) = {
   Call("GET", "/assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                        

                      
    
}
                            

// @LINE:6
class ReverseLanding {
    


 
// @LINE:6
def index() = {
   Call("GET", "/")
}
                                                        

                      
    
}
                            

// @LINE:10
// @LINE:9
class ReverseApiSchedule {
    


 
// @LINE:10
def apply(uri:String) = {
   Call("POST", "/api/schedules/" + implicitly[PathBindable[String]].unbind("uri", uri))
}
                                                        
 
// @LINE:9
def byUri(uri:String) = {
   Call("GET", "/api/schedules/" + implicitly[PathBindable[String]].unbind("uri", uri))
}
                                                        

                      
    
}
                            
}
                    


// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:13
class ReverseAssets {
    


 
// @LINE:13
def at = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"/assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                                                        

                      
    
}
                            

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
                                                        

                      
    
}
                            

// @LINE:10
// @LINE:9
class ReverseApiSchedule {
    


 
// @LINE:10
def apply = JavascriptReverseRoute(
   "controllers.ApiSchedule.apply",
   """
      function(uri) {
      return _wA({method:"POST", url:"/api/schedules/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("uri", uri)})
      }
   """
)
                                                        
 
// @LINE:9
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
                    


// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {

// @LINE:13
class ReverseAssets {
    


 
// @LINE:13
def at(path:String, file:String) = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]))
)
                              

                      
    
}
                            

// @LINE:6
class ReverseLanding {
    


 
// @LINE:6
def index() = new play.api.mvc.HandlerRef(
   controllers.Landing.index(), HandlerDef(this, "controllers.Landing", "index", Seq())
)
                              

                      
    
}
                            

// @LINE:10
// @LINE:9
class ReverseApiSchedule {
    


 
// @LINE:10
def apply(uri:String) = new play.api.mvc.HandlerRef(
   controllers.ApiSchedule.apply(uri), HandlerDef(this, "controllers.ApiSchedule", "apply", Seq(classOf[String]))
)
                              
 
// @LINE:9
def byUri(uri:String) = new play.api.mvc.HandlerRef(
   controllers.ApiSchedule.byUri(uri), HandlerDef(this, "controllers.ApiSchedule", "byUri", Seq(classOf[String]))
)
                              

                      
    
}
                            
}
                    
                