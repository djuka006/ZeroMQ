using System;
using NetMQ;
using System.Threading;


namespace Worker
{
    class Program
    {
        static void Main(string[] args)
        {
            using (NetMQContext ctx = NetMQContext.Create())
            {
                using (var receiver = ctx.CreatePullSocket())
                {
                    receiver.Connect("tcp://localhost:5557");
                    using (var sender = ctx.CreatePushSocket())
                    {
                        sender.Connect("tcp://localhost:5558");
                        while (true)
                        {
                            var message = receiver.ReceiveString();
                            Console.WriteLine(message);
                            Thread.Sleep(int.Parse(message));
                            sender.Send("");
                        }
                    }
                }
            }
        }
    }

}
