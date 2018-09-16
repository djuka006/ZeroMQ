using System;
using NetMQ;

namespace AsyncReqRepClient
{
    class Program
    {
        static void Main(string[] args)
        {
            using (NetMQContext ctx = NetMQContext.Create())
            {
                using (var requester = ctx.CreateRequestSocket())
                {
                    int id = new Random().Next(100);
                    requester.Connect("tcp://localhost:5559");
                    int requestNumber = 0;
                    for (; requestNumber < 10; ++requestNumber)
                    {
                        requester.Send(string.Format("Hello (from {0})", id));
                        string message = requester.ReceiveString();
                        Console.WriteLine(string.Format("Received reply {0} [{1}]", requestNumber, message));
                    }
                }
            }
        }

    }
}
