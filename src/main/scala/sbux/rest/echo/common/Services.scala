package sbux.rest.echo.common

import akka.http.scaladsl.server.{RequestContext, Route}
import org.apache.log4j.lf5.LogLevel
import sbux.rest.echo.models.EchoResponse

trait LogService {
  def log(msg: String, level: LogLevel)

  def log(msg: String, obj: AnyRef, level: LogLevel)
}

// this is responsible for aggregating all RouteServices
trait HttpService {
  def routes: Route
}

// this could have multiple implementation, each provides a route
trait RouteService {
  def route: Route
}

// this represents a particular service, each RouteService could have a dedicated ServiceHandler
trait ServiceHandler {
  def HandleGet(ctx: RequestContext): EchoResponse
}
