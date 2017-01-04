package sbux.rest.echo.services

import org.apache.log4j.lf5.LogLevel
import sbux.rest.echo.common.LogService

// for simplicity, it's accepting only string, but we really should
// accept object
class LogServiceConsoleImpl extends LogService {

  override def log(msg: String, level: LogLevel): Unit = println(s"[$level] - $msg")

  override def log(msg: String, obj: AnyRef, level: LogLevel): Unit = println(s"[$level] - $msg - " + obj)
}