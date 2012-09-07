
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.api.templates.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import com.avaje.ebean._
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object main extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(title: String)(content: Html):play.api.templates.Html = {
        _display_ {import helper._

implicit def /*3.2*/implicitFieldConstructor/*3.26*/ = {{ FieldConstructor(helpers.TwitBootInput.render) }};
Seq[Any](format.raw/*1.32*/("""
"""),format.raw/*3.80*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq[Any](/*9.17*/title)),format.raw/*9.22*/("""</title>
        <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*10.70*/routes/*10.76*/.Assets.at("stylesheets/bootstrap.css"))),format.raw/*10.115*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*11.54*/routes/*11.60*/.Assets.at("stylesheets/main.css"))),format.raw/*11.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*12.59*/routes/*12.65*/.Assets.at("images/favicon.png"))),format.raw/*12.97*/("""">
        
    <style type="text/css">
      body """),format.raw("""{"""),format.raw/*15.13*/("""
        padding-top: 60px;
        padding-bottom: 40px;
      """),format.raw("""}"""),format.raw/*18.8*/("""
    </style>
	
	    <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(Seq[Any](/*21.67*/routes/*21.73*/.Assets.at("stylesheets/main.css"))),format.raw/*21.107*/("""">
        <script type="text/javascript" src=""""),_display_(Seq[Any](/*22.46*/routes/*22.52*/.Assets.at("javascripts/jquery/jquery.js"))),format.raw/*22.94*/(""""></script>
        <script type="text/javascript" src=""""),_display_(Seq[Any](/*23.46*/routes/*23.52*/.Assets.at("javascripts/charts/js/highcharts.src.js"))),format.raw/*23.105*/(""""></script>
        <script type="text/javascript" src=""""),_display_(Seq[Any](/*24.46*/routes/*24.52*/.Assets.at("javascripts/app/chart.js"))),format.raw/*24.90*/(""""></script>
	

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">        
        
    </head>
    <body>
        """),_display_(Seq[Any](/*41.10*/content)),format.raw/*41.17*/("""
    </body>
</html>
"""))}
    }
    
    def render(title:String,content:Html) = apply(title)(content)
    
    def f:((String) => (Html) => play.api.templates.Html) = (title) => (content) => apply(title)(content)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Aug 31 11:26:43 MDT 2012
                    SOURCE: C:/software/hackr/sellstat/app/views/main.scala.html
                    HASH: 82b34d620830f2c37eda21b670e12ec7c0d406c6
                    MATRIX: 759->1|874->50|906->74|990->31|1018->128|1106->181|1132->186|1246->264|1261->270|1323->309|1415->365|1430->371|1486->405|1583->466|1598->472|1652->504|1751->556|1862->621|1980->703|1995->709|2052->743|2136->791|2151->797|2215->839|2308->896|2323->902|2399->955|2492->1012|2507->1018|2567->1056|3412->1865|3441->1872
                    LINES: 27->1|30->3|30->3|31->1|32->3|38->9|38->9|39->10|39->10|39->10|40->11|40->11|40->11|41->12|41->12|41->12|44->15|47->18|50->21|50->21|50->21|51->22|51->22|51->22|52->23|52->23|52->23|53->24|53->24|53->24|70->41|70->41
                    -- GENERATED --
                */
            