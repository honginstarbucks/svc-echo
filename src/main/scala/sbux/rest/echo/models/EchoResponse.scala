package sbux.rest.echo.models

case class EchoResponse(message: String, headers: Map[String, String],
                        counter: Int)
