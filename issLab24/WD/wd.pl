%====================================================================================
% wd description   
%====================================================================================
dispatch( start, start(N) ).
dispatch( stop, stop(N) ).
dispatch( on, on(N) ).
dispatch( off, off(N) ).
dispatch( sonar, sonar(N) ).
request( foto, foto(N) ).
reply( rplFoto, rplFoto(N) ).  %%for foto
request( recogn, recogn(N) ).
reply( rplRecogn, rplRecogn(N) ).  %%for recogn
%====================================================================================
context(ctxstation, "127.0.0.1",  "TCP", "8081").
context(ctxrasp, "localhost",  "TCP", "8082").
context(ctximg, "127.0.0.1",  "TCP", "8083").
 qactor( station, ctxstation, "external").
  qactor( img, ctximg, "external").
  qactor( rasp, ctxrasp, "it.unibo.rasp.Rasp").
 static(rasp).
  qactor( blueled, ctxrasp, "it.unibo.blueled.Blueled").
 static(blueled).
  qactor( sonar, ctxrasp, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( camera, ctxrasp, "it.unibo.camera.Camera").
 static(camera).
