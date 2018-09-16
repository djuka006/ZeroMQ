var zmq = require('zmq'),
 frontend = zmq.socket('router'),
 backend = zmq.socket('dealer');

frontend.on('message', function() {
  var args = Array.apply(null, arguments);
  backend.send(args);
});

backend.on('message', function() {
  var args = Array.apply(null, arguments);
  frontend.send(args);
});

frontend.bindSync('tcp://*:5559');
backend.bindSync('tcp://*:5560');
