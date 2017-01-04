package sbux.rest.echo.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpcirce.CirceSupport
import sbux.rest.echo.common.{RouteService, ServiceHandler}
import io.circe.syntax._
import io.circe.parser._
import io.circe.generic.auto._
import scala.concurrent.ExecutionContext

class EchoRoute(val service: ServiceHandler)(implicit ec: ExecutionContext)
  extends RouteService
  with CirceSupport {

  import service._

  def route: Route = pathPrefix("echo") {
    getHandler()
  }

  def getHandler(): Route = pathEndOrSingleSlash {
    get {
      ctx =>
        ctx.complete {
          HandleGet(ctx).asJson
        }
    }
  }
}

