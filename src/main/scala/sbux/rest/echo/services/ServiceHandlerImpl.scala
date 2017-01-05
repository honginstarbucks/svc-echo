package sbux.rest.echo.services

import akka.http.scaladsl.server.RequestContext
import sbux.rest.echo.Counter
import sbux.rest.echo.common.ServiceHandler
import sbux.rest.echo.config.AppConfig
import sbux.rest.echo.models.EchoResponse

class ServiceHandlerImpl extends ServiceHandler {

  println(s"Starting count from: ${AppConfig.counter}")

  var counter = Counter(AppConfig.counter)
  override def HandleGet(ctx: RequestContext): EchoResponse =
    {
      counter = Counter.increment(counter)
      val resp : EchoResponse = EchoResponse(
        "Hello World",
        ctx.request.headers.map(h=>h.name() -> h.value()).toMap,
        counter.count
      )
      resp
    }
}
