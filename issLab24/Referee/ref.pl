%====================================================================================
% ref description   
%====================================================================================
dispatch( ball, ball(N) ).
dispatch( ballview, ball(N) ).
request( info, info(X) ).
reply( obsinfo, obsinfo(X) ).  %%for info
%====================================================================================
context(ctxppref, "localhost",  "TCP", "8014").
 qactor( ping, ctxppref, "it.unibo.ping.Ping").
 static(ping).
  qactor( pong, ctxppref, "it.unibo.pong.Pong").
 static(pong).
  qactor( referee, ctxppref, "it.unibo.referee.Referee").
 static(referee).
