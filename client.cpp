#include "client.h"
#include "cc.h"

Client::Client():isQuit(0),msgSize(0)
{
    char p1[]="add";
    assgin(add,p1,3);
    char p2[]="delete";
    assgin(delet,p2,6);
    char p3[]="search";
    assgin(search,p3,6);
    char p4[]="!help";
    assgin(help,p4,5);
    char p5[]="!quit";
    assgin(quit,p5,5);
    char p6[]="name";
    assgin(name,p6,4);
    char p7[]="number";
    assgin(number,p7,6);
    char p8[]="address";
    assgin(address,p8,8);

    Initial();
}

bool Client::getCmd()
{
    string s;

    cout<<"ad>";
    cin.getline(msg,256);

    if(isEqual(msg,add))
    {
        //name
        s.append("a;");
        cout<<"name:";
        cin.getline(msg,256);
        s.append(msg);

        //number
        s.append(1,';');
        cout<<"number:";
        cin.getline(msg,256);
        s.append(msg);

        //address
        s.append(1,';');
        cout<<"address:";
        cin.getline(msg,256);
        s.append(msg);
    }
    else if(isEqual(msg,delet))
    {
        s.append("d;");
        cout<<"delete by(name|number|address):";
        cin.getline(msg,256);
        if(isEqual(msg,name))
        {
            cout<<"name:";
            cin.getline(msg,256);
            s.append(msg);
        }
        else if(isEqual(msg,number))
        {
            cout<<"number:";
            cin.getline(msg,256);
            s.append(msg);
        }
        else if(isEqual(msg,address))
        {
            cout<<"address:";
            cin.getline(msg,256);
            s.append(msg);
        }
        else
        {
            cout<<"Input format error,please input !help for detail"<<endl;
            return false;
        }
    }
    else if(isEqual(msg,search))
    {
        s.append("s;");
        cout<<"search by(name|number|address):";
        cin.getline(msg,256);
        if(isEqual(msg,name))
        {
            cout<<"name:";
            cin.getline(msg,256);
            s.append(msg);
        }
        else if(isEqual(msg,number))
        {
            cout<<"number:";
            cin.getline(msg,256);
            s.append(msg);
        }
        else if(isEqual(msg,address))
        {
            cout<<"address:";
            cin.getline(msg,256);
            s.append(msg);
        }
        else
        {
            cout<<"Input format error,please input !help for detail"<<endl;
            return false;
        }
    }
    else if(isEqual(msg,help))
    {
        s.append("!h");
        cout<<"Input add----\tfor adding contact"<<endl;
        cout<<"\tfor example"<<endl;
        cout<<"\tname: xiaoming"<<endl;
        cout<<"\tmobile: 18888888888"<<endl;
        cout<<"\taddress: xiaoming jia"<<endl;

        cout<<"Input search\t----for searching contact"<<endl;
        cout<<"Input delete\t----for deleting contact"<<endl;
        cout<<"Input !help\t----for help"<<endl;
        cout<<"Input !quit\t----for quit"<<endl;
    }
    else if(isEqual(msg,quit))
    {
        s.append("!q");
        isQuit=1;
    }
    else
    {
        cout<<"Input format error,please input !help for detail"<<endl;
        return false;
    }

    int j=s.length();
    if(j>1023)
    {
        cout<<"It is too long"<<endl;
        return false;
    }

    int i;
    for(i=0;i<j;i++)
        msg[i]=s[i];
    msg[i]='\0';
    msgSize=i+1;

    return true;

}

bool Client::send()
{
    Send(msg,msgSize);
    return true;
}

bool Client::receive()
{
    Receive(msg,1024);
    cout<<msg<<endl;
    return true;
}

bool Client::isEqual(char *p1, char *p2)//(buf,goal)
{
    int i=0;
    for(;;i++)
    {
        if(p1[i]==p2[i])
        {
            if(p1[i]=='\0')
                break;
        }
        else
            return false;
    }
    return true;
}

bool Client::assgin(char *p1, char *p2,int len)
{
    int i;
    for(i=0;i<len;i++)
        p1[i]=p2[i];
    p1[len]='\0';
}

Client::~Client()
{
    EndSesstion();
}

int main(int args,char *atgv[])
{
    Client client;


    while(!client.isQuit)
    {
        if(!client.getCmd())
            continue;
        client.send();
        client.receive();
    }

    return 0;
}
