using System;

using NetMQ;

namespace HelloWorld
{
    class Program
    {
        static void Main(string[] args)
        {
            string connection = "inproc://HelloWorld";
            using (NetMQContext ctx = NetMQContext.Create())
            {
                using (var server = ctx.CreateResponseSocket())
                {
                    server.Bind(connection);
                    using (var client = ctx.CreateRequestSocket())
                    {
                        client.Connect(connection);

                        client.Send("Hello");

                        string fromClientMessage = server.ReceiveString();
                        Console.WriteLine("From Client: {0}", fromClientMessage);
                        server.Send("Hi Back");

                        string fromServerMessage = client.ReceiveString();
                        Console.WriteLine("From Server: {0}", fromServerMessage);

                        Console.ReadLine();
                    }
                }
            }
        }
    }
}
