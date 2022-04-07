package it.training.scala.http

import sttp.client3.quick._
import sttp.client3.circe._
import io.circe.generic.auto._

object HttpExample extends App {

  val query = "http language:scala"
  val sort: String = "stars"
  val order: String = "desc"
  val pPage: Int = 50

  val request = basicRequest
                .get(uri"https://api.github.com/search/repositories?q=$query&sort=$sort&order=$order&per_page=$pPage")
                .response(asJson[GitHubResponse])

  val response = request.send(backend)

  response.body match {
    case Left(e) => println(s"Error executing request: $e")
    case Right(data) => {
      println(s"Found ${data.total_count} projects")
      println(s"Showing ${data.items.size} with most stars:")
      data.items.foreach( item => {
        println(s" - ${item.name} (${item.stargazers_count})")
      })
    }
  }
}

case class GitHubResponse(total_count: Int, items: List[GitHubItem])
case class GitHubItem(name: String, stargazers_count: Int)