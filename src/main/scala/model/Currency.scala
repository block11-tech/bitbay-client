package model

import ca.mrvisser.sealerate
import play.api.libs.json._

sealed abstract class Currency(val name: String, val code: String)

object Currency {

  case object PLN extends Currency("polski złoty", "PLN")
  case object EUR extends Currency("euro", "EUR")
  case object USD extends Currency("dolar", "USD")
  case object CHF extends Currency("frank", "CHF")
  case object GBP extends Currency("funt", "GBP")
  case object AUD extends Currency("AUD", "AUD")
  case object CAD extends Currency("CAD", "CAD")
  case object CZK extends Currency("CZK", "CZK")
  case object DKK extends Currency("DKK", "DKK")
  case object HUF extends Currency("HUF", "HUF")
  case object JPY extends Currency("JPY", "JPY")
  case object NOK extends Currency("NOK", "NOK")
  case object RUB extends Currency("RUB", "RUB")
  case object SEK extends Currency("SEK", "SEK")

  case object BTC extends Currency("bitcoin", "BTC")
  case object BTG extends Currency("bitcoin gold", "BTG")

  def values: Set[Currency] = sealerate.values[Currency]

  def apply(code: String): Currency = values.find(_.code == code).getOrElse(throw new Exception(s"Can't constructs currency from code: $code"))

  implicit val readsCurrency: Reads[Currency] = new Reads[Currency] {
    override def reads(json: JsValue) = json match {
      case JsString(s) => JsSuccess(Currency(s))
      case _ => JsError("Invalid Currency structure")
    }
  }
}
