#ifndef CLIENT_H
#define CLIENT_H

#include <iostream>
#include <string>

using namespace std;

class Client
{
public:
    Client();
    ~Client();
    //send message of msg to server
    bool send();

    //receive reponse from server
    //and output response's result
    bool receive();

    //get an input cmd and its arg,
    //then save them to msg
    bool getCmd();

    //msg sending to server
    char msg[1024];

    //the sizeof msg
    int msgSize;

    //mark whether exit
    bool isQuit;

private:
    //check cmd
    bool isEqual(char *p1,char *p2);

    //
    bool assgin(char *p1,char *p2,int len);

    //save cmd expected
    char add[4];
    char delet[7];
    char search[7];
    char help[6];
    char quit[6];
    char name[5];
    char number[7];
    char address[8];


};

#endif
