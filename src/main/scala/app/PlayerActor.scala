
package com.ziyasal.peksimet.app

import akka.actor.{Actor, Props}

class PlayerActor extends Actor {


  val player = this.context.actorOf(Props[UsersActor])
  val reporting = this.context.actorOf(Props[ReportingActor])


  def notify(event: PlayEvent) {
    player ! event
    reporting ! event
  }

  def receive = {
    case evt: PlayEvent => notify(evt)
    case _ => println("Unknown message")
  }
}