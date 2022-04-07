import sttp.client3.quick._
import sttp.client3.circe._
import io.circe.generic.auto._

object PersonGeneratorAPI {
  def main(args: Array[String]): Unit = {
    val randomPersons = (1 to 10)
                              .map(
                                x => fetchRandomPerson().getOrElse(None)
                              ).toList

    randomPersons.foreach(
      p=> println(s"$p")
    )
  }

  def fetchRandomPerson(): Option[Person] = {
    val apiUrl = "https://randomuser.me/api/"

    val output = basicRequest
      .get(uri"$apiUrl")
      .response(asJson[ApiResponse])
      .send(backend)

    output.body match {
      case Left(e) => {
        println(s"Error: $e")
        return None
      }
      case Right(data) => {
        val item = data.results.head
        return Some(
          Person(
            data.info.seed,
            item.name.first,
            item.name.last,
            item.gender,
            item.location.country
          ))
      }
    }
  }
}

case class Person(id: String, firstName: String, lastName: String, gender: String, country: String)

case class ApiResponse(results: List[Item], info: ItemInfo)
case class ItemInfo(seed: String)
case class Item(gender: String, name: ItemName, location: ItemLocation)
case class ItemName(first: String, last: String)
case class ItemLocation(country: String)