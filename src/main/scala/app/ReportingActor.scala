package com.ziyasal.peksimet.app

import akka.actor.Actor

import scala.collection.immutable.ListMap

class ReportingActor extends Actor {

  var counters = Map[String, Int]()

  def registerView(asset: String): Unit = {

    val exists: Boolean = counters.exists(p => p._1 == asset)

    if (exists) {
      val value = counters.getOrElse(asset, 0) + 1
      counters = counters + (asset -> value)
    } else {
      counters = counters + (asset -> 1)
    }
  }

  def printReport(): Unit = {
    ListMap(counters.toSeq.sortBy(_._1): _*).foreach(kv => {
      println("%d\t%s".format( kv._2, kv._1))
    })
  }

  def receive = {
    case evt: PlayEvent => {
      registerView(evt.asset)
      printReport()
    }
    case _ => println("Unknown event")
  }
}
