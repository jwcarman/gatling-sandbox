package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpProtocol = http
    //.baseUrl("http://localhost:8080")
    //.baseUrl("https://192.168.50.171")
    .baseUrl("http://192.168.50.171:18080")
    .acceptHeader("text/plain") // Here are the common headers

  val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .exec(http("hello")
      .get("/hello/Foo"))

  setUp(
    scn.inject(nothingFor(4 seconds),
      rampUsersPerSec(10) to 100 during (1 minutes),
      constantUsersPerSec(100) during (3 minutes))
    .protocols(httpProtocol))
}
