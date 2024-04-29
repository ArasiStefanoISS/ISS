%====================================================================================
% bw description   
%====================================================================================
event( vrinfo, vrinfo(A,B,C) ).
dispatch( move, move(M) ).
dispatch( sonarname, name(N) ).
dispatch( distance, distance(D) ).
dispatch( axys, axys(K) ).
%====================================================================================
context(ctxwb, "localhost",  "TCP", "8088").
context(ctxwenv, "127.0.0.1",  "TCP", "8090").
 qactor( vrobot, ctxwenv, "external").
  qactor( walkboundary, ctxwb, "it.unibo.walkboundary.Walkboundary").
 static(walkboundary).
