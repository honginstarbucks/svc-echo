package sbux.rest.echo

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import sbux.rest.echo.common.Module

import scala.concurrent.ExecutionContext
import scala.io.StdIn

object app extends App {
  implicit val actorSystem = ActorSystem()
  implicit val executor: ExecutionContext = actorSystem.dispatcher
  implicit val log: LoggingAdapter = Logging(actorSystem, getClass)
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val module = new Module {}

  val httpService = module.httpService
  val bindingFuture = Http().bindAndHandle(httpService.routes, "0.0.0.0", 8080)
  println(s"server is starting at 0.0.0.0:8080\n")
  //StdIn.readLine()
  //actorSystem.terminate()
}
