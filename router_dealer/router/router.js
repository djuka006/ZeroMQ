var zmq = require('zeromq'),
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

frontend.connect('tcp://localhost:3000');
backend.connect('tcp://localhost:3001');
