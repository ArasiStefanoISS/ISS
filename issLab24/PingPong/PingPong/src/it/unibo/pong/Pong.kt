/* Generated by AN DISI Unibo */ 
package it.unibo.pong

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

class Pong ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outred("Pong START")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="handlePing",cond=whenRequest("hit"))
				}	 
				state("handlePing") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("hit(N)"), Term.createTerm("hit(A)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								delay(1000) 
								if(  Math.random()*100+1 <= 90  
								 ){answer("hit", "hit_resp", "hit(a)"   )  
								CommUtils.outred("Pong")
								}
								else
								 {CommUtils.outred("YOU LOSE")
								 answer("hit", "miss_resp", "miss(a)"   )  
								  System.exit(0)  
								 }
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t01",targetState="handlePing",cond=whenRequest("hit"))
					transition(edgeName="t02",targetState="handleMiss",cond=whenDispatch("miss"))
				}	 
				state("handleMiss") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("miss(N)"), Term.createTerm("miss(N)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								CommUtils.outred("YOU WIN")
								 System.exit(0)  
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 