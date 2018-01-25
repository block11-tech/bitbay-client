package gateways.bitbay

import javax.inject.Inject

import com.roundeights.hasher.Algo
import gateways.bitbay.bitbayModel.{MyBitbayTrades}
import model.CurrencyPair
import org.joda.time.DateTime
import play.api.libs.json.{JsError, JsResult}
import play.api.libs.ws.WSClient
import play.api.{Configuration, Logger}

import scala.concurrent.{ExecutionContext, Future}

class BitbayPrivateGateway @Inject() (ws: WSClient, config: Configuration)(implicit ec: ExecutionContext) {

  private val publicKey = config.get[String]("exchanges.bitbay.publicKey")
  private val privateKey = config.get[String]("exchanges.bitbay.privateKey")

  def getMyTransactions(start: DateTime, end: DateTime, pair: CurrencyPair): Future[JsResult[MyBitbayTrades]] = {

    val time = DateTime.now().getMillis / 1000
    println (time)
    val params = ""//"""{"markets”:["BTC-PLN"]}"""
    val algo = Algo.hmac(privateKey).sha512
    val data = s"$publicKey$time$params"
    println(data)
    val hash: String = algo(data).hex

    ws.url(s"https://api.bitbay.net/rest/trading/history/transactions")
//      .addQueryStringParameters("query" -> params)
      .addHttpHeaders("API-Key" -> publicKey,
        "API-Hash" -> hash,
      "Request-Timestamp" -> s"$time")
      .withFollowRedirects(true)
      .get
      .map { response =>
        Logger.error(response.toString)
        Logger.error(response.body)
        response.status match {
          case 200 =>
            println(response.body)
            val jsResult = response.json.validate[MyBitbayTrades]
            jsResult
          case code: Int =>
            Logger.error(response.toString)
            Logger.error(response.body)
            JsError(s"Invalid response code:$code")
        }
      }
  }

}
