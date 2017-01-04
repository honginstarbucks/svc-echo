package sbux.rest.echo.common

import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.model.headers._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._

trait CorsSupport {
  lazy val allowedOriginHeader = {
    val sAllowedOrigin = "*" // TODO: update this from config
    if (sAllowedOrigin == "*") {
      `Access-Control-Allow-Origin`.*
    }
    else {
      `Access-Control-Allow-Origin`(HttpOrigin(sAllowedOrigin))
    }
  }

  private def addAccessControlHeaders: Directive0 = {
    mapResponseHeaders { headers =>
      allowedOriginHeader +:
        `Access-Control-Allow-Credentials`(true) +:
        `Access-Control-Allow-Headers`("Token", "Content-Type", "X-Requested-With") +: headers
    }
  }

  private def preflightRequestHandler: Route = options {
    complete(HttpResponse(StatusCodes.OK).withHeaders(
      `Access-Control-Allow-Methods`(OPTIONS, POST, PUT, GET, DELETE)
    ))
  }

  def corsHandler(r: Route): Route = addAccessControlHeaders(
    preflightRequestHandler ~ r
  )
}
