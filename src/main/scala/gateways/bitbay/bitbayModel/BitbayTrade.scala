package gateways.bitbay.bitbayModel

import play.api.libs.json._

case class MyBitbayTrade(id: String, market: String, time: String, amount: BigDecimal, rate: BigDecimal, initializedBy: String, wasTaker: Boolean, userAction: String)

object MyBitbayTrade {

  implicit val format = Json.format[MyBitbayTrade]
}

case class MyBitbayTrades(status: String, items: Seq[MyBitbayTrade])

object MyBitbayTrades {
  implicit val format = Json.format[MyBitbayTrades]
}

