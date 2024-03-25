%====================================================================================
% helloworld0 description   
%====================================================================================
dispatch( info, info(N) ).
dispatch( ack, ack(N) ).
%====================================================================================
context(ctx0, "localhost",  "TCP", "8000").
 qactor( consumer, ctx0, "it.unibo.consumer.Consumer").
 static(consumer).
  qactor( producer, ctx0, "it.unibo.producer.Producer").
 static(producer).
  qactor( producer2, ctx0, "it.unibo.producer2.Producer2").
 static(producer2).
