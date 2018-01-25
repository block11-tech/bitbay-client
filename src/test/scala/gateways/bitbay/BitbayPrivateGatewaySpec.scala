package gateways.bitbay

import model.CurrencyPair.BTCPLN
import org.joda.time.DateTime
import org.scalatest.concurrent.{AbstractPatienceConfiguration, ScalaFutures}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{Matchers, WordSpec}
import play.api.inject.guice.GuiceApplicationBuilder

class BitbayPrivateGatewaySpec extends WordSpec with Matchers with GeneratorDrivenPropertyChecks
  with ScalaFutures with AbstractPatienceConfiguration {

  implicit val defaultPatience = PatienceConfig(timeout = Span(20, Seconds), interval = Span(10, Millis))

  val injector = new GuiceApplicationBuilder()
    .injector()
  val gateway = injector.instanceOf[BitbayPrivateGateway]

  "Bitbay private gateway" should {
    "provide trades history" in {
      val history = gateway.getMyTransactions(DateTime.now(), DateTime.now(), BTCPLN).futureValue
      println(history)
      history.isSuccess shouldBe true
    }
  }
}
