import sttp.client3.quick._
import sttp.client3.circe._
import io.circe.generic.auto._

case class Item(Nation: String, Year: String, Population: Long)
case class ApiReponse(data: List[Item])

val apiUrl = "https://datausa.io/api/data?drilldowns=Nation&measures=Population"

val output = basicRequest
                          .get(uri"$apiUrl")
                          .response(asJson[ApiReponse])
                          .send(backend)

println(s"Response status: ${output.statusText}")
output.body match {
  case Left(e) => println(s"Error: $e")
  case Right(res) => {
    res.data.foreach( item => {
      println(
        s"""
           |Item
           |-------
           |nation: ${item.Nation}
           |year: ${item.Year}
           |population: ${item.Population}
           |
           |""".stripMargin)
    })
  }
}
