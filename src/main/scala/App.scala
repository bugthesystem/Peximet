package com.ziyasal.peksimet

import akka.actor.{Actor, ActorSystem, Address, Props}
import akka.cluster.Cluster
import akka.stream.ActorMaterializer

class SomeActor extends Actor {
  def receive = {
    case "666" => println("666 O_o")
    case _ => println("Oooo ooo, we sleep until the sun goes down!")
  }
}

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

  val someActor = actorSystem.actorOf(Props[SomeActor], name = "Tractor")
  someActor ! "666"
  someActor ! "-1"
}
