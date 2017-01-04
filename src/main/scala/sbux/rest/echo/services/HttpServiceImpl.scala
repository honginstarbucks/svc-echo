package sbux.rest.echo.services

import scala.concurrent.ExecutionContext
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import sbux.rest.echo.common.{CorsSupport, HttpService, RouteService}

class HttpServiceImpl(val pocRoute: RouteService)(implicit ec: ExecutionContext)
  extends HttpService
    with CorsSupport {

  def routes: Route =
    pathPrefix("v1") {
      corsHandler(
        pocRoute.route
      )
    }
}
