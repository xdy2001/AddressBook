#include "server.h"
#include "contact.h"
#include "cs.h"

using namespace std;

Server::Server()
{
    //configue server
    Initial();
}

void Server::accept()
{
    Accept(msg,sizeof(msg));
}

void Server::response()
{
    //add cmd
    if(msg[0]=='a')
    {
        Contact c;
        int i=2;

        //name
        while(msg[i]!=';')
        {
            c.name.append(1,msg[i]);
            i++;
        }
        i++;

        //number
        while(msg[i]!=';')
        {
            c.number.append(1,msg[i]);
            i++;
        }
        i++;

        //address
        while(msg[i]!='\0')
        {
            c.address.append(1,msg[i]);
            i++;
        }
        if(ad.add(c))
            stringTochar("Add successful!");
        else
            stringTochar("Add fail!");
    }
   //delete cmd
    else if(msg[0]=='d')
    {
        string s;
        int i=2;

        while(msg[i]!='\0')
        {
            s.append(1,msg[i]);
            i++;
        }

        if(ad.remove(s))
            stringTochar("Delete successful!");
        else
            stringTochar("Delete fail");
    }
    //search cmd
    else if(msg[0]=='s')
    {
        string s;
        int i=2;

        while(msg[i]!='\0')
        {
            s.append(1,msg[i]);
            i++;
        }
        pList it;
        if(ad.search(s,it))
        {
            string s;
            s.append("name");
            s.append(1,':');
            s.append(it->name);
            s.append(1,'\n');

            s.append("number");
            s.append(1,':');
            s.append(it->number);
            s.append(1,'\n');

            s.append("address");
            s.append(1,':');
            s.append(it->address);
            stringTochar(s);
        }
        else
        {
            stringTochar("The person don't exit");
        }

    }
    //help or quit cmd
    else if(msg[0]=='!')
    {
        if(msg[1]=='h')
        {//content of help
            stringTochar(" ");
        }
        else if(msg[1]=='q')
        {
            ad.isQuit=1;
            stringTochar("Quit successfuly!");
        }
    }
    Response(msg,sizeof(msg));
}

void Server::stringTochar(const string &s)
{
    int l=s.length();
    int i;
    for(i=0;i<l;i++)
        msg[i]=s[i];
    msg[i]='\0';
}

Server::~Server()
{
    //End connection
    EndSesstion();
}
int main()
{
    Server server;

    while(!server.ad.isQuit)
    {
        server.accept();
        server.response();
    }
    return 0;
}
