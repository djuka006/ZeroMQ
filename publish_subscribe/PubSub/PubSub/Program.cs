using System;
using NetMQ;

namespace PubSub
{
    sealed class Program
    {
        public static void Main(string[] args)
        {
            using (NetMQContext ctx = NetMQContext.Create())
            {
                using (var subscriber = ctx.CreateSubscriberSocket())
                {
                    subscriber.Connect("tcp://localhost:5556");
                    int id = new Random().Next(100000);
                    subscriber.Subscribe(id.ToString("D5"));

                    long totalData = 0;
                    int updateNumber = 0;
                    for (; updateNumber < 100; updateNumber++)
                    {
                        string message = subscriber.ReceiveString();
                        totalData += int.Parse(message.Split(' ')[1]);
                    }

                    Console.WriteLine(string.Format("Average data for id {0} was {1}",
                        id, (totalData / updateNumber)));
                }
            }
        }
    }

}
