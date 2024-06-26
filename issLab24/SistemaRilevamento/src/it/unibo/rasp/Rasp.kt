/* Generated by AN DISI Unibo */ 
package it.unibo.rasp

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024

class Rasp ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "start"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("start") { //this:State
					action { //it:State
						CommUtils.outblue("Raspberry START")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="beginHandle",cond=whenDispatch("start"))
				}	 
				state("beginHandle") { //this:State
					action { //it:State
						CommUtils.outblue("Beginning work")
						forward("start", "start(0)" ,"sonar" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t01",targetState="sonarHandle",cond=whenDispatch("sonar"))
					transition(edgeName="t02",targetState="stopHandle",cond=whenDispatch("stop"))
				}	 
				state("sonarHandle") { //this:State
					action { //it:State
						forward("off", "off(0)" ,"blueled" ) 
						forward("on", "on(0)" ,"blueled" ) 
						request("foto", "foto(0)" ,"camera" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t03",targetState="imgRecog",cond=whenReply("rplFoto"))
					transition(edgeName="t04",targetState="ledHandle",cond=whenReply("rplRecogn"))
					transition(edgeName="t05",targetState="stopHandle",cond=whenDispatch("stop"))
				}	 
				state("imgRecog") { //this:State
					action { //it:State
						request("recogn", "recogn(0)" ,"img" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="sonarHandle", cond=doswitch() )
				}	 
				state("ledHandle") { //this:State
					action { //it:State
						forward("on", "on(0)" ,"station" ) 
						delay(1500) 
						forward("off", "off(0)" ,"blueled" ) 
						forward("off", "off(0)" ,"station" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="sonarHandle", cond=doswitch() )
				}	 
				state("stopHandle") { //this:State
					action { //it:State
						forward("stop", "stop(0)" ,"blueled" ) 
						forward("stop", "stop(0)" ,"camera" ) 
						forward("stop", "stop(0)" ,"sonar" ) 
						CommUtils.outblue("Raspberry STOP")
						delay(500) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 
