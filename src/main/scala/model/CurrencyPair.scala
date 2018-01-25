package model

import ca.mrvisser.sealerate
import model.Currency.{BTC, BTG, EUR, PLN}

sealed abstract class CurrencyPair(val name: String, val code: String, val leftCurrency: Currency, val rightCurrency: Currency)

object CurrencyPair {

  case object BTCPLN extends CurrencyPair("BTC/PLN", "BTCPLN", BTC, PLN)
  case object BTCEUR extends CurrencyPair("BTC/EUR", "BTCEUR", BTC, EUR)

  //bitcoingold
  case object BTGPLN extends CurrencyPair("BTG/PLN", "BTGPLN", BTG, PLN)

  def values: Set[CurrencyPair] = sealerate.values[CurrencyPair]
}
