package sbux.rest.echo.common

import sbux.rest.echo.routes.EchoRoute
import sbux.rest.echo.services.{HttpServiceImpl, LogServiceConsoleImpl, ServiceHandlerImpl}

import scala.concurrent.ExecutionContext

trait Module {

  import com.softwaremill.macwire._

  implicit val ec = ExecutionContext.global
  lazy val logger: LogService = wire[LogServiceConsoleImpl]
  lazy val handler: ServiceHandler = wire[ServiceHandlerImpl]
  lazy val route: RouteService = wire[EchoRoute]
  lazy val httpService: HttpService = wire[HttpServiceImpl]
}
