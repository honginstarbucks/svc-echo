package sbux.rest.echo.config

import com.typesafe.config.ConfigFactory

object AppConfig {
  private val config = ConfigFactory.load()
  protected val appConfig = config.getConfig("app")
  val counter = appConfig.getInt("counter.start")
}
