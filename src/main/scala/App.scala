package com.ziyasal.peksimet

import akka.actor.{ActorSystem, Address, Props}
import akka.cluster.Cluster
import akka.stream.ActorMaterializer
import com.ziyasal.peksimet.app.{PlayEvent, PlayerActor}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.Random

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {
  private val CLUSTER_NAME = "PeximetActorSystem"
  val actorSystem = ActorSystem(CLUSTER_NAME)
  actorSystem.actorOf(SimpleClusterListener.props)
  val materializer = ActorMaterializer.create(actorSystem)
  val cluster = Cluster.get(actorSystem)

  val addresses = System.getenv().get("SEED_NODES").split(",")
    .map(ip => new Address("akka.tcp", CLUSTER_NAME, ip, 2551))
    .toList

  cluster.joinSeedNodes(addresses)

  run()

  def run(): Unit = {
    val users = Array("Poseidon", "Afrodite", "Hercules", "Ares", "Zeus", "Thor", "Loki")
    val assets = Array("Kardesimin Hikayesi", "Saatleri Ayarlama Enstitusu", "Fareler ve Insanlar", "Sanat Nedir", "Dava")


    val player = actorSystem.actorOf(Props[PlayerActor])

    while (true) {
      val f = Future {
        Thread.sleep(2000)
      }.map { s => {
        player ! PlayEvent(
          user = users(Random.nextInt(users.length)),
          asset = assets(Random.nextInt(assets.length))
        )
      }
      }


      Await.ready(f, Duration.Inf)

    }

    actorSystem.terminate()
  }
}
