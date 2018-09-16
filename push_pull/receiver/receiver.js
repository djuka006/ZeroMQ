var zmq = require('zmq'),
 receiver = zmq.socket('pull');

var started = false,
 i = 0,
 label = "Total elapsed time";

receiver.on('message', function() {
  if (!started) {
    console.time(label);
    started = true;

  } else {
    i += 1;
    process.stdout.write(i % 10 === 0 ? ':' : '.');
    if (i === 100) {
      console.timeEnd(label);
      receiver.close();
      process.exit();
    }
  }
});

receiver.bindSync("tcp://*:5558");
